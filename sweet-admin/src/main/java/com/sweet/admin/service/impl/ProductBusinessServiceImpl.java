package com.sweet.admin.service.impl;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.img.ImgUtil;
import com.sweet.admin.service.ProductBusinessService;
import com.sweet.common.constant.AdminConstant;
import com.sweet.common.enums.ProductStatusEnum;
import com.sweet.common.util.DateFormatUtil;
import com.sweet.service.dto.*;
import com.sweet.service.entity.*;
import com.sweet.service.service.*;
import com.sweet.service.vo.ProductDetailWithRelationsVO;
import com.sweet.service.vo.ProductSkuVO;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.*;

/**
 * 商品业务 Service 实现类
 */
@Slf4j
@Service
@AllArgsConstructor
public class ProductBusinessServiceImpl implements ProductBusinessService {

    private final ProductService productService;
    private final ProductSkuService productSkuService;
    private final ProductAttributeRelationService productAttributeRelationService;
    private final ProductSkuAttributeRelationService productSkuAttributeRelationService;
    private final CloudflareR2Service cloudflareR2Service;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long createProduct(ProductDTO dto) {
        log.info("createProduct dto:{}", dto);

        List<ProductAttributeRelationDTO> attributeRelations = dto.getAttributeRelations();
        List<ProductSkuDTO> skus = dto.getSkus();

        // 1. 参数校验
        Assert.hasText(dto.getProductName(), "商品名称不能为空");
        //Assert.hasText(dto.getSubTitle(), "商品副标题不能为空");
       // Assert.hasText(dto.getDetail(), "商品详情不能为空");
        Assert.notNull(dto.getPrice(), "商品价格不能为空");
        Assert.notNull(dto.getCostPrice(), "商品成本价不能为空");
        Assert.notNull(dto.getStock(), "商品库存不能为空");
        Assert.notNull(dto.getUnit(), "商品单位不能为空");
        Assert.notNull(dto.getMarketPrice(), "商品市场价不能为空");
        Assert.notNull(dto.getMemberPrice(), "商品会员价不能为空");
        Assert.notNull(dto.getStoreId(), "门店 ID 不能为空");
        Assert.notNull(dto.getCategoryId(), "分类 ID 不能为空");
        Assert.notEmpty(dto.getImages(), "商品图片不能为空");
        Assert.notEmpty(dto.getSliderImages(), "商品轮播图不能为空");
        Assert.notEmpty(dto.getSkus(), "商品 SKU 不能为空");
        Assert.notEmpty(attributeRelations, "商品属性关联不能为空");

        // 2. 构建并保存商品主表
        Product product = buildProduct(dto);
        productService.save(product);
        Long productId = product.getId();

        // 3. 保存商品属性关联
        saveAttributeRelations(productId, attributeRelations);

        // 4. 保存商品 SKU 及属性关联
        saveSkusAndRelations(productId, skus);

        log.info("createProduct success productId:{}", productId);
        return productId;
    }

    @Override
    public List<String> uploadProductImages(ProductUploadDto dto) {
        try {

            //参数校验
            Long productCategoryId = dto.getProductCategoryId();
            Long storeId = dto.getStoreId();
            String type = dto.getType();
            List<MultipartFile> files = dto.getFiles();

            Assert.notNull(productCategoryId, "商品分类 ID 不能为空");
            Assert.notNull(storeId, "门店 ID 不能为空");
            Assert.hasText(type, "上传类型不能为空");
            Assert.notEmpty(files, "图片列表不能为空");

            //上传
            Map<String, InputStream> imageKeyToInputStream = new HashMap<>();
            List<String> imageKeys = new ArrayList<>();
            List<String> urls = new ArrayList<>();
            for (MultipartFile image : files) {
                String key = String.format("%s/%s-%s-%s-%s.%s",
                        AdminConstant.OSS_PRODUCT_PREFIX_SPU,
                        storeId,
                        productCategoryId,
                        type,
                        DateFormatUtil.formatMilliTimestamp(System.currentTimeMillis(), DatePattern.PURE_DATETIME_PATTERN),
                        ImgUtil.IMAGE_TYPE_PNG);
                imageKeyToInputStream.put(key, image.getInputStream());
                imageKeys.add(key);
            }
            cloudflareR2Service.putBatchObjects(imageKeyToInputStream);

            //返回上传后的url列表
            for (String imageKey : imageKeys) {
                String url = cloudflareR2Service.generateUploadUrl(imageKey);
                urls.add(url);
            }
            return urls;
        }catch (Exception e){
            throw new RuntimeException("上传商品图片失败：" + e.getMessage(), e);
        }
    }

    @Override
    public Boolean deleteProductImages(List<String> imageUrls) {
        if(CollectionUtils.isEmpty(imageUrls)){
            return true;
        }

        for (String imageUrl : imageUrls) {
            cloudflareR2Service.deleteObject(imageUrl);
        }

        return true;
    }

    /**
     * 构建商品实体
     */
    private Product buildProduct(ProductDTO dto) {
        return new Product()
                .setProductName(dto.getProductName())
                .setSubTitle(dto.getSubTitle())
                .setDescription(dto.getDescription())
                .setDetail(dto.getDetail())
                .setStoreId(dto.getStoreId())
                .setCategoryId(dto.getCategoryId())
                .setPrice(dto.getPrice())
                .setMarketPrice(dto.getMarketPrice())
                .setMemberPrice(dto.getMemberPrice())
                .setCostPrice(dto.getCostPrice())
                .setStock(dto.getStock())
                .setUnit(dto.getUnit())
                .setTags(dto.getTags())
                .setSort(dto.getSort())
                .setIsHot(dto.getIsHot())
                .setIsRecommend(dto.getIsRecommend())
                .setIsNew(dto.getIsNew())
                .setStatus(dto.getStatus() == null ? ProductStatusEnum.NORMAL.getCode() : dto.getStatus())
                .setImages(convertImages(dto.getImages()))
                .setSliderImages(convertImages(dto.getSliderImages()));
    }

    /**
     * 转换图片列表
     */
    private List<Map<String, Object>> convertImages(List<ProductImageDTO> images) {
        if (CollectionUtils.isEmpty(images)) {
            return null;
        }
        return images.stream().map(img -> {
                    Map<String, Object> imageMap = new HashMap<>();
                    imageMap.put(AdminConstant.IMAGE_JSONB_KEY_URL, img.getUrl());
                    imageMap.put(AdminConstant.IMAGE_JSONB_KEY_SORT, img.getSort());
                    imageMap.put(AdminConstant.IMAGE_JSONB_KEY_DESCRIPTION, img.getDescription());
                    return imageMap;
                }).toList();
    }

    /**
     * 校验商品属性关联
     */
    private void validateAttributeRelation(ProductAttributeRelationDTO dto) {
        Assert.notNull(dto.getAttributeId(), "属性 ID 不能为空");
        Assert.notNull(dto.getAttributeValueId(), "属性值 ID 不能为空");
    }

    /**
     * 校验 SKU 属性关联
     */
    private void validateSkuAttributeRelation(ProductSkuAttributeRelationDTO dto) {
        Assert.notNull(dto.getAttributeId(), "属性 ID 不能为空");
        Assert.notNull(dto.getAttributeValueId(), "属性值 ID 不能为空");
    }

    /**
     * 保存商品属性关联
     */
    private void saveAttributeRelations(Long productId, List<ProductAttributeRelationDTO> relations) {
        if(CollectionUtils.isEmpty(relations)){
            return;
        }

        List<ProductAttributeRelation> saveProductAttributeRelations = new ArrayList<>();

        for (ProductAttributeRelationDTO attributeRelationDTO : relations) {

            // 属性校验
            validateAttributeRelation(attributeRelationDTO);

            ProductAttributeRelation relation = new ProductAttributeRelation()
                    .setProductId(productId)
                    .setAttributeId(attributeRelationDTO.getAttributeId())
                    .setAttributeValueId(attributeRelationDTO.getAttributeValueId())
                    .setRequired(attributeRelationDTO.getRequired())
                    .setSort(attributeRelationDTO.getSort());
            saveProductAttributeRelations.add(relation);
        }

        if (!CollectionUtils.isEmpty(saveProductAttributeRelations)) {
            productAttributeRelationService.saveBatch(saveProductAttributeRelations);
        }
    }

    /**
     * 保存商品 SKU 及属性关联
     */
    private void saveSkusAndRelations(Long productId, List<ProductSkuDTO> skus) {
        if (CollectionUtils.isEmpty(skus)) {
            return;
        }

        // 收集 SKU 和属性关联
        List<ProductSkuAttributeRelation> saveSkuAttributeRelations = new ArrayList<>();
        for (ProductSkuDTO dto : skus) {

            //保存sku,下面的属性关联表需要用到sku_id
            ProductSku sku = new ProductSku()
                    .setProductId(productId)
                    .setSpecs(dto.getSpecs())
                    .setPrice(dto.getPrice())
                    .setStock(dto.getStock())
                    .setImages(convertImages(dto.getImages()))
                    .setStatus(dto.getStatus());
            productSkuService.save(sku);

            //构建sku_attribute_relation等待批量保存
            List<ProductSkuAttributeRelationDTO> skuAttributeRelations = dto.getAttributeRelations();

            if(CollectionUtils.isEmpty(skuAttributeRelations)){
                continue;
            }

            for (ProductSkuAttributeRelationDTO skuAttributeRelation : skuAttributeRelations) {
                // SKU 属性校验
                validateSkuAttributeRelation(skuAttributeRelation);

                ProductSkuAttributeRelation skuRelation = new ProductSkuAttributeRelation()
                        .setSkuId(sku.getId())
                        .setAttributeId(skuAttributeRelation.getAttributeId())
                        .setAttributeValueId(skuAttributeRelation.getAttributeValueId())
                        .setSort(skuAttributeRelation.getSort());
                saveSkuAttributeRelations.add(skuRelation);
            }
        }

        // 批量保存属性关联
        if (!CollectionUtils.isEmpty(saveSkuAttributeRelations)) {
            productSkuAttributeRelationService.saveBatch(saveSkuAttributeRelations);
        }
    }

    /**
     * 更新 SKU 及属性关联 product_sku and product_sku_attribute_relation
     */
    private void updateSkusAndRelations(Long productId, List<ProductSkuDTO> skus) {

        // 删除原有的 SKU 及关联
        deleteSkusAndRelations(productId);

        // 如果要更新的列表为空则无需触发更新
        if (CollectionUtils.isEmpty(skus)) {
            return;
        }

        // 收集 SKU 和属性关联
        List<ProductSkuAttributeRelation> saveSkuAttributeRelations = new ArrayList<>();
        for (ProductSkuDTO dto : skus) {

            // 保存 SKU
            ProductSku sku = new ProductSku()
                    .setProductId(productId)
                    .setSpecs(dto.getSpecs())
                    .setPrice(dto.getPrice())
                    .setStock(dto.getStock())
                    .setImages(convertImages(dto.getImages()))
                    .setStatus(dto.getStatus());
            productSkuService.save(sku);

            // 构建 SKU 属性关联等待批量保存
            List<ProductSkuAttributeRelationDTO> skuAttributeRelations = dto.getAttributeRelations();
            if (CollectionUtils.isEmpty(skuAttributeRelations)) {
                continue;
            }

            for (ProductSkuAttributeRelationDTO skuAttributeRelation : skuAttributeRelations) {
                // SKU 属性校验
                validateSkuAttributeRelation(skuAttributeRelation);

                ProductSkuAttributeRelation skuRelation = new ProductSkuAttributeRelation()
                        .setSkuId(sku.getId())
                        .setAttributeId(skuAttributeRelation.getAttributeId())
                        .setAttributeValueId(skuAttributeRelation.getAttributeValueId())
                        .setSort(skuAttributeRelation.getSort());
                saveSkuAttributeRelations.add(skuRelation);
            }
        }

        // 批量保存属性关联
        if (!CollectionUtils.isEmpty(saveSkuAttributeRelations)) {
            productSkuAttributeRelationService.saveBatch(saveSkuAttributeRelations);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean updateProduct(ProductDTO dto) {
        log.info("updateProduct dto:{}", dto);

        // 1. 参数校验
        Assert.notNull(dto.getId(), "商品 ID 不能为空");
        Assert.hasText(dto.getProductName(), "商品名称不能为空");
        Assert.notNull(dto.getStoreId(), "门店 ID 不能为空");
        Assert.notNull(dto.getCategoryId(), "分类 ID 不能为空");

        Long productId = dto.getId();

        // 2. 更新商品主表
        Product product = buildProduct(dto);
        product.setId(productId);
        productService.updateById(product);

        // 3. 更新商品属性关联
        updateAttributeRelations(productId, dto.getAttributeRelations());

        // 4. 更新 SKU 及属性关联
        updateSkusAndRelations(productId, dto.getSkus());

        log.info("updateProduct success productId:{}", productId);
        return true;
    }

    /**
     * 更新商品属性关联 product_attrbute_relation
     */
    private void updateAttributeRelations(Long productId, List<ProductAttributeRelationDTO> relations) {

        //删除原来的关联信息列表
        productAttributeRelationService.removeByProductId(productId);

        //如果要更新的列表为空则无需触发更新
        if (CollectionUtils.isEmpty(relations)) {
            return;
        }

        //触发新增
        List<ProductAttributeRelation> insertList = relations.stream()
                .map(dto -> new ProductAttributeRelation()
                        .setProductId(productId)
                        .setAttributeId(dto.getAttributeId())
                        .setAttributeValueId(dto.getAttributeValueId())
                        .setRequired(dto.getRequired())
                        .setSort(dto.getSort()))
                .toList();
        productAttributeRelationService.saveBatch(insertList);
    }


    /**
     * 删除数据 product_sku and product_sku_attribute_relation
     */
    private void deleteSkusAndRelations(Long productId) {
        List<Long> skuIds = productSkuService.getSkuIdsByProductId(productId);
        productSkuAttributeRelationService.removeBySkuIds(skuIds);
        productSkuService.removeByProductId(productId);
    }

    @Override
    public ProductDetailWithRelationsVO getProductDetailById(Long productId) {

        // 1. 获取商品信息
        Product product = productService.getById(productId);
        if (Objects.isNull(product)) {
            throw new RuntimeException("商品不存在,请检查productId");
        }

        // 2. 获取商品属性关联列表
        List<ProductAttributeRelation> attributeRelations = productAttributeRelationService.getByProductId(productId);

        // 3. 获取商品 SKU 列表并组装属性关联
        List<ProductSku> skus = productSkuService.getByProductId(productId);
        List<ProductSkuVO> skuVOs = new ArrayList<>();
        for (ProductSku sku : skus) {
            List<ProductSkuAttributeRelation> skuAttributeRelations = productSkuAttributeRelationService.getBySkuId(sku.getId());
            ProductSkuVO skuVO = new ProductSkuVO();
            BeanUtils.copyProperties(sku, skuVO);
            skuVO.setAttributeRelations(skuAttributeRelations);
            skuVOs.add(skuVO);
        }

        // 4. 组装返回结果
        return new ProductDetailWithRelationsVO()
                .setProduct(product)
                .setAttributeRelations(attributeRelations)
                .setSkus(skuVOs);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean deleteProductById(Long productId) {

        // 检查商品是否存在
        Product product = productService.getById(productId);
        Assert.notNull(product, "商品不存在,请检查productId");

        // 删除 SKU 及关联属性
        deleteSkusAndRelations(productId);

        // 删除商品属性关联
        productAttributeRelationService.removeByProductId(productId);

        // 删除商品主表
        return productService.removeById(productId);
    }
}
