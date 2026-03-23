package com.sweet.service.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sweet.common.constant.AdminConstant;
import com.sweet.common.enums.CategoryStatusEnum;
import com.sweet.common.util.FileExtUtil;
import com.sweet.service.dto.ProductCategoryDto;
import com.sweet.service.entity.ProductCategory;
import com.sweet.service.mapper.ProductCategoryMapper;
import com.sweet.service.service.CloudflareR2Service;
import com.sweet.service.service.ProductCategoryService;
import com.sweet.service.vo.ProductCategoryNodeVo;
import com.sweet.service.vo.ProductCategoryOptionVo;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

@Slf4j
@Service
@AllArgsConstructor
public class ProductCategoryServiceImpl extends BaseServiceImpl<ProductCategoryMapper, ProductCategory> implements ProductCategoryService {

    private final CloudflareR2Service cloudflareR2Service;

    @Override
    public Page<ProductCategory> getPage(Integer pageNo, Integer pageSize, Long storeId, String categoryName, Integer status) {
        Page<ProductCategory> page = new Page<>(pageNo, pageSize);

        return super.page(page, Wrappers.<ProductCategory>lambdaQuery()
                .eq(Objects.nonNull(storeId), ProductCategory::getStoreId, storeId)
                .like(StringUtils.hasText(categoryName), ProductCategory::getCategoryName, categoryName)
                .eq(Objects.nonNull(status), ProductCategory::getStatus, status)
                .orderByAsc(ProductCategory::getSort, ProductCategory::getId));
    }

    @Override
    public boolean save(ProductCategoryDto dto) {
        log.info("product category save dto:{}", dto);

        // 参数处理
        Long storeId = dto.getStoreId();
        String categoryName = dto.getCategoryName();
        MultipartFile iconFile = dto.getIconFile();

        Assert.notNull(storeId, "门店 ID 不能为空");
        Assert.hasText(categoryName, "分类名称不能为空");

        // 保存实体
        ProductCategory entity = new ProductCategory();
        BeanUtils.copyProperties(dto, entity);
        entity.setCategoryName(categoryName);
        entity.setStoreId(storeId);
        boolean ret = super.save(entity);

        // 上传图标（如果提供了文件）
        Long id = entity.getId();
        if (Objects.nonNull(iconFile) && !iconFile.isEmpty()) {
            String iconUrl = uploadIcon(storeId, id, iconFile);
            entity.setIcon(iconUrl);
            super.updateById(entity);
            log.info("product category save id:{}, iconUrl:{}", id, iconUrl);
        }

        return ret;
    }

    @Override
    public boolean updateById(ProductCategoryDto dto) {
        log.info("product category update dto:{}", dto);

        // 参数校验
        Long id = dto.getId();
        Long storeId = dto.getStoreId();
        String categoryName = dto.getCategoryName();
        MultipartFile iconFile = dto.getIconFile();

        Assert.notNull(id, "分类 ID 不能为空");
        Assert.notNull(storeId, "门店 ID 不能为空");
        Assert.hasText(categoryName, "分类名称不能为空");

        // 获取旧数据
        ProductCategory oldEntity = super.getById(id);
        String oldIconUrl = oldEntity.getIcon();

        // 上传新图标（如果提供了文件）
        String iconUrl = oldIconUrl;
        if (Objects.nonNull(iconFile) && !iconFile.isEmpty()) {

            // 删除旧图标
            if (StringUtils.hasText(oldIconUrl)) {
                deleteIcon(oldIconUrl);
            }

            // 上传新图标
            iconUrl = uploadIcon(storeId, id, iconFile);
            log.info("product category update upload id:{}, iconUrl:{}", id, iconUrl);
        }

        // 更新实体
        ProductCategory entity = new ProductCategory();
        BeanUtils.copyProperties(dto, entity);
        entity.setIcon(iconUrl);
        return super.updateById(entity);
    }

    @Override
    public List<ProductCategory> getAppProductCategories(Long storeId) {
        QueryWrapper<ProductCategory> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda()
                .eq(ProductCategory::getStoreId, storeId)
                .eq(ProductCategory::getStatus, CategoryStatusEnum.NORMAL.getCode())
                .eq(ProductCategory::getParentId, 0);
        return super.list(queryWrapper);
    }

    /**
     * 上传分类图标到 Cloudflare R2
     * @param storeId 门店 ID
     * @param categoryId 分类 ID
     * @param iconFile 图标文件
     * @return 上传后的 URL
     */
    private String uploadIcon(Long storeId, Long categoryId, MultipartFile iconFile) {
        try {

            // 生成文件名
            String fileExtension = FileExtUtil.getExtension(iconFile.getOriginalFilename());
            String key = String.format("%s/%s-%s.%s",
                    AdminConstant.OSS_PRODUCT_PREFIX_CATEGORY,
                    storeId,
                    categoryId,
                    fileExtension);

            // 上传到 R2
            cloudflareR2Service.putObject(key, iconFile.getInputStream());
            return cloudflareR2Service.generateUploadUrl(key);
        } catch (Exception e) {
            log.error("uploadIcon failed", e);
            throw new RuntimeException("上传图标失败：" + e.getMessage(), e);
        }
    }

    /**
     * 删除分类图标
     * @param iconUrl 图标 URL
     */
    private void deleteIcon(String iconUrl) {
        log.info("deleteIcon iconUrl:{}", iconUrl);
        try {
            java.net.URI uri = java.net.URI.create(iconUrl);
            String key = uri.getPath();
            cloudflareR2Service.deleteObject(key);
        } catch (Exception e) {
            log.error("deleteIcon failed", e);
            throw new RuntimeException("删除图标失败：" + e.getMessage(), e);
        }
    }

    @Override
    public List<ProductCategoryNodeVo> getCategoryTree(Long storeId) {
        List<ProductCategory> categories = super.list(Wrappers.<ProductCategory>lambdaQuery()
                .eq(Objects.nonNull(storeId), ProductCategory::getStoreId, storeId)
                .orderByAsc(ProductCategory::getSort, ProductCategory::getId));

        return buildCategoryTree(categories);
    }

    @Override
    public List<ProductCategoryOptionVo> getProductCategoryOptions(Long storeId) {
        List<ProductCategory> categories = super.list(Wrappers.<ProductCategory>lambdaQuery()
                .eq(Objects.nonNull(storeId), ProductCategory::getStoreId, storeId));

        if (CollectionUtils.isEmpty(categories)) {
            return List.of();
        }
        return categories
                .stream()
                .filter(Objects::nonNull)
                .map(category -> new ProductCategoryOptionVo()
                        .setId(category.getId())
                        .setCategoryName(category.getCategoryName())
                        .setStatus(category.getStatus())
                )
                .toList();
    }

    @Override
    public boolean removeCategory(Long id) {
        if (Objects.isNull(id)) {
            return false;
        }

        // 检查是否存在子分类
        long count = super.count(Wrappers.<ProductCategory>lambdaQuery()
                .eq(ProductCategory::getParentId, id));

        if (count > 0) {
            throw new RuntimeException("该分类下存在子分类，无法删除");
        }

        return super.removeById(id);
    }

    /**
     * 构建商品分类树
     * @param categories 分类列表
     * @return 树形结构
     */
    private List<ProductCategoryNodeVo> buildCategoryTree(List<ProductCategory> categories) {
        if (CollectionUtils.isEmpty(categories)) {
            return List.of();
        }

        // 数据结构转换
        List<ProductCategoryNodeVo> categoryNodeVos = new ArrayList<>();
        Map<Long, ProductCategoryNodeVo> categoryNodeVoMap = new HashMap<>();

        for (ProductCategory category : categories) {
            ProductCategoryNodeVo categoryNodeVo = new ProductCategoryNodeVo();
            BeanUtils.copyProperties(category, categoryNodeVo);
            categoryNodeVos.add(categoryNodeVo);
            categoryNodeVoMap.put(category.getId(), categoryNodeVo);
        }

        // 构建父子关系
        List<ProductCategoryNodeVo> categoryTrees = new ArrayList<>();
        for (ProductCategoryNodeVo categoryNodeVo : categoryNodeVos) {
            Long id = categoryNodeVo.getId();
            Integer parentId = categoryNodeVo.getParentId();

            // 根节点处理（parentId 为 0 或 null）
            if (Objects.isNull(parentId) || parentId == 0) {
                categoryTrees.add(categoryNodeVoMap.get(id));
                continue;
            }

            // 子节点处理
            ProductCategoryNodeVo parentNode = categoryNodeVoMap.get(parentId.longValue());
            if (Objects.nonNull(parentNode)) {
                parentNode.getChildren().add(categoryNodeVo);
            } else {
                // 如果找不到父节点，作为根节点处理
                categoryTrees.add(categoryNodeVoMap.get(id));
            }
        }

        // 补全是否存在子节点状态
        for (ProductCategoryNodeVo categoryNodeVo : categoryTrees) {
            List<ProductCategoryNodeVo> children = categoryNodeVo.getChildren();
            if (!CollectionUtils.isEmpty(children)) {
                categoryNodeVo.setHasChildren(true);
            }
        }

        return categoryTrees;
    }
}
