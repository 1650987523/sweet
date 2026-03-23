package com.sweet.app.service.impl;

import com.sweet.app.mapper.ProductBusinessMapper;
import com.sweet.app.service.ProductBusinessService;
import com.sweet.app.dto.OrderCreateReqDto;
import com.sweet.app.vo.OrderCreateVo;
import com.sweet.app.vo.SelectSkuVo;
import com.sweet.common.enums.OrderStatusEnum;
import com.sweet.common.enums.OrderTypeEnum;
import com.sweet.common.util.OrderNoGeneratorUtil;
import com.sweet.service.entity.Attribute;
import com.sweet.service.entity.AttributeValue;
import com.sweet.service.entity.OrderDetail;
import com.sweet.service.entity.OrderMain;
import com.sweet.service.entity.Product;
import com.sweet.service.entity.ProductAttributeRelation;
import com.sweet.service.entity.ProductCategory;
import com.sweet.service.entity.ProductSku;
import com.sweet.service.entity.Store;
import com.sweet.service.service.StoreService;
import com.sweet.service.service.AttributeService;
import com.sweet.service.service.AttributeValueService;
import com.sweet.service.service.OrderDetailService;
import com.sweet.service.service.OrderMainService;
import com.sweet.service.service.ProductAttributeRelationService;
import com.sweet.service.service.ProductCategoryService;
import com.sweet.service.service.ProductService;
import com.sweet.service.service.ProductSkuService;
import com.sweet.service.vo.ProductAttributeWithValuesVo;
import com.sweet.service.vo.ProductAttributeValueVo;
import com.sweet.service.vo.ProductSimpleVo;
import com.sweet.service.vo.ProductWithAttributeVo;
import com.sweet.service.vo.ProductWithCategoryVo;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 商品业务服务实现类（小程序端）
 */
@Service
@Slf4j
@AllArgsConstructor
public class ProductBusinessServiceImpl implements ProductBusinessService {

    private final ProductCategoryService productCategoryService;
    private final ProductService productService;
    private final ProductAttributeRelationService productAttributeRelationService;
    private final AttributeService attributeService;
    private final AttributeValueService attributeValueService;
    private final ProductSkuService productSkuService;
    private final ProductBusinessMapper productBusinessMapper;
    private final OrderMainService orderMainService;
    private final OrderDetailService orderDetailService;
    private final StoreService storeService;

    @Override
    public List<ProductWithCategoryVo> getProductsWithCategories(Long storeId, String productName) {
        List<ProductWithCategoryVo> productWithCategoryVos = new ArrayList<>();

        // 商品分类数据加入到列表
        List<ProductCategory> productCategories = productCategoryService.getAppProductCategories(storeId);
        if (CollectionUtils.isEmpty(productCategories)) {
            return productWithCategoryVos;
        }

        List<Long> categoryIds = new ArrayList<>();
        for (ProductCategory productCategory : productCategories) {
            categoryIds.add(productCategory.getId());

            ProductWithCategoryVo productWithCategoryVo = new ProductWithCategoryVo();
            productWithCategoryVo.setCategoryId(productCategory.getId());
            productWithCategoryVo.setCategoryName(productCategory.getCategoryName());
            productWithCategoryVos.add(productWithCategoryVo);
        }

        // 商品信息数据加入到列表
        List<Product> products = productService.getAppProducts(storeId, categoryIds, productName);
        if (CollectionUtils.isEmpty(products)) {
            return productWithCategoryVos;
        }

        for (ProductWithCategoryVo productWithCategoryVo : productWithCategoryVos) {
            Long categoryId = productWithCategoryVo.getCategoryId();
            List<ProductSimpleVo> productSimpleVos = new ArrayList<>();

            for (Product product : products) {

                Long productCategoryId = product.getCategoryId();

                if (categoryId.equals(productCategoryId)) {
                    ProductSimpleVo productSimpleVo = new ProductSimpleVo();
                    productSimpleVo.setId(product.getId());
                    productSimpleVo.setProductName(product.getProductName());
                    productSimpleVo.setProductDescription(product.getDescription());
                    productSimpleVo.setProductPrice(product.getPrice());
                    productSimpleVo.setImages(product.getImages());

                    productSimpleVos.add(productSimpleVo);
                }
            }

            productWithCategoryVo.setProducts(productSimpleVos);
        }

        return productWithCategoryVos;
    }

    @Override
    public ProductWithAttributeVo getProductWithAttributeInfo(Long productId) {

        // 商品信息
        Product product = productService.getById(productId);

        // 商品属性关联信息
        List<ProductAttributeRelation> productAttributeRelations = productAttributeRelationService.getByProductId(productId);
        if (CollectionUtils.isEmpty(productAttributeRelations)) {
            throw new RuntimeException("商品属性关联信息为空");
        }

        // 提取属性 ID 和属性值 ID（JDK 21 stream）
        List<Long> attributeIds = productAttributeRelations.stream()
                .map(ProductAttributeRelation::getAttributeId)
                .collect(Collectors.toList());
        List<Long> attributeValueIds = productAttributeRelations.stream()
                .map(ProductAttributeRelation::getAttributeValueId)
                .collect(Collectors.toList());

        // 批量获取属性和属性值
        List<Attribute> attributes = attributeService.listByIds(new ArrayList<>(attributeIds));
        List<AttributeValue> attributeValues = attributeValueService.listByIds(new ArrayList<>(attributeValueIds));

        List<ProductAttributeWithValuesVo> attributeVos = new ArrayList<>();
        for (Attribute attribute : attributes) {

            Long id = attribute.getId();

            ProductAttributeWithValuesVo attributeVo = new ProductAttributeWithValuesVo();
            attributeVo.setAttributeId(id);
            attributeVo.setAttributeName(attribute.getName());
            List<ProductAttributeValueVo> attributeValueVos = new ArrayList<>();
            for (AttributeValue attributeValue : attributeValues) {

                Long attributeId = attributeValue.getAttributeId();

                if (id.equals(attributeId)) {
                    ProductAttributeValueVo productAttributeValueVo = new ProductAttributeValueVo();
                    productAttributeValueVo.setId(attributeValue.getId())
                            .setAttributeId(attributeValue.getAttributeId())
                            .setValue(attributeValue.getValue())
                            .setSort(attributeValue.getSort())
                            .setStatus(attributeValue.getStatus() == 1);
                    attributeValueVos.add(productAttributeValueVo);
                }
            }

            attributeVo.setAttributeValues(attributeValueVos);
            attributeVos.add(attributeVo);

        }

        // 封装返回结果
        return new ProductWithAttributeVo()
                .setProduct(product)
                .setAttributes(attributeVos);
    }

    @Override
    public ProductSku getSkuInfoByAttrs(com.sweet.service.vo.ProductSkuQueryVo productSkuQueryVo) {
        log.info("getSkuInfoByAttrs productSkuQueryVo:{}", productSkuQueryVo);

        Integer count = productSkuQueryVo.getCount();
        Long productId = productSkuQueryVo.getProductId();
        List<com.sweet.service.vo.ProductSkuQueryVo.SelectedSpecVo> sku = productSkuQueryVo.getSku();

        Assert.notNull(count, "数量不能为空");
        Assert.notNull(productId, "商品 ID 不能为空");
        Assert.notEmpty(sku, "规格条件不能为空");
        if (count <= 0) {
            throw new IllegalArgumentException("数量必须大于 0");
        }

        // 根据规格匹配 SKU ID
        SelectSkuVo selectSkuVo = productBusinessMapper.selectSkuIdByAttrs(sku, productId, sku.size());
        Assert.notNull(selectSkuVo, "没有匹配的 SKU");
        Long matchedSkuId = selectSkuVo.getSkuId();

        // 根据 skuId 和 productId 查询 SKU 信息
        ProductSku productSku = productSkuService.getSku(matchedSkuId, productId);
        return productSku;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public OrderCreateVo createOrder(OrderCreateReqDto req) {
        log.info("createOrder req:{}", req);

        // 1. 参数校验
        Assert.notNull(req.getStoreId(), "店铺 ID 不能为空");
        Assert.notNull(req.getUserId(), "用户 ID 不能为空");
        Assert.notNull(req.getTableNo(), "桌台号不能为空");
        List<OrderCreateReqDto.SkuItem> skus = req.getSkus();
        if (Optional.ofNullable(skus).orElse(List.of()).isEmpty()) {
            throw new IllegalArgumentException("商品列表不能为空");
        }

        // 2. 校验店铺是否存在
        Store store = storeService.getById(req.getStoreId().intValue());
        Assert.notNull(store, "店铺不存在");

        // 3. 处理订单明细并扣减库存
        List<OrderDetail> orderDetails = processOrderDetails(req.getSkus());

        // 4. 计算订单金额
        Long skuTotalAmount = orderDetails.stream()
                .mapToLong(OrderDetail::getSubtotal)
                .sum();
        Long discountAmount = Optional.ofNullable(req.getDiscountAmount()).orElse(0L);
        Long payAmount = Math.max(0, skuTotalAmount - discountAmount);
        log.info("createOrder skuTotalAmount:{}, discountAmount:{}, payAmount:{}", skuTotalAmount, discountAmount, payAmount);

        // 5. 创建订单
        String orderNo = generateOrderNo(req.getStoreId(), req.getUserId(), req.getTableNo());
        log.info("createOrder orderNo:{}", orderNo);
        OrderMain orderMain = buildOrderMain(req, store, orderNo, skuTotalAmount, discountAmount, payAmount);
        orderMainService.save(orderMain);

        // 6. 保存订单明细
        orderDetails.forEach(detail -> detail.setOrderNo(orderNo));
        orderDetailService.saveBatch(orderDetails);

        // 7. 返回结果
        return new OrderCreateVo()
                .setOrderId(orderMain.getId())
                .setOrderNo(orderNo)
                .setTotalAmount(skuTotalAmount)
                .setPayAmount(payAmount)
                .setOrderStatus(orderMain.getOrderStatus());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean cancelOrder(String orderNo, Long userId) {
        log.info("cancelOrder orderNo:{}, userId:{}", orderNo, userId);

        // 1. 参数校验
        Assert.hasText(orderNo, "订单号不能为空");
        Assert.notNull(userId, "用户 ID 不能为空");

        // 2. 查询订单（同时校验订单归属）
        OrderMain orderMain = orderMainService.getInfo(orderNo, userId.intValue());
        Assert.notNull(orderMain, "订单不存在");

        // 3. 校验订单状态（只有待支付状态的订单可以取消）
        Assert.isTrue(OrderStatusEnum.WAIT_PAY.getCode().equals(orderMain.getOrderStatus()),
                "只有待支付状态的订单可以取消");

        // 4. 更新订单状态为已取消
        boolean updated = orderMainService.updateStatus(orderNo, OrderStatusEnum.CANCELLED.getCode());
        Assert.isTrue(updated, "取消订单失败");

        // 6. 获取订单明细并恢复库存
        List<OrderDetail> orderDetails = orderMainService.getOrderDetailsByOrderNo(orderNo);
        if (!CollectionUtils.isEmpty(orderDetails)) {
            for (OrderDetail detail : orderDetails) {
                Long skuId = detail.getSkuId();
                Integer quantity = detail.getQuantity();
                // 恢复库存（同步方式，保证事务一致性,update方式是原子性的,如果同时触发了扣减库存,那么扣减库存会去排队）
                productSkuService.addStock(skuId, quantity);
            }
        }

        log.info("cancelOrder success, orderNo:{}", orderNo);
        return true;
    }

    /**
     * 处理订单明细：校验 SKU、扣减库存、查询商品、计算金额
     */
    private List<OrderDetail> processOrderDetails(List<OrderCreateReqDto.SkuItem> skus) {
        if (CollectionUtils.isEmpty(skus)) {
            throw new IllegalArgumentException("商品列表不能为空");
        }

        List<OrderDetail> orderDetails = new ArrayList<>();
        for (OrderCreateReqDto.SkuItem sku : skus) {
            Long skuId = sku.getSkuId();
            Integer count = sku.getCount();
            Long productId = sku.getProductId();

            //确认商品存在
            Product product = productService.getById(productId);
            Assert.notNull(product, "商品不存在：" + productId);

            //sku-行锁
            ProductSku productSku = productSkuService.selectForUpdate(skuId);
            Assert.notNull(productSku, "SKU 不存在：" + skuId);

            //sku-库存扣减
            boolean success = productSkuService.reduceStock(skuId, count);
            if (!success) {
                throw new RuntimeException("商品库存不足：" + productId);
            }

            // 计算小计并构建订单明细
            Long subtotal = productSku.getPrice() * count;
            OrderDetail orderDetail = new OrderDetail()
                    .setProductId(productId)
                    .setSkuId(skuId)
                    .setProductName(product.getProductName())
                    .setPrice(productSku.getPrice())
                    .setQuantity(count)
                    .setSkuSpecs(productSku.getSpecs())
                    .setSubtotal(subtotal)
                    .setSkuImages(product.getImages());
            orderDetails.add(orderDetail);
        }
        return orderDetails;
    }

    /**
     * 构建订单主表对象
     */
    private OrderMain buildOrderMain(OrderCreateReqDto req, Store store, String orderNo,
                                      Long skuTotalAmount, Long discountAmount, Long payAmount) {
        return new OrderMain()
                .setOrderNo(orderNo)
                .setUserId(req.getUserId().intValue())
                .setStoreId(req.getStoreId().intValue())
                .setStoreName(store.getName())
                .setOrderType(OrderTypeEnum.DINE_IN.getCode())
                .setOrderStatus(OrderStatusEnum.WAIT_PAY.getCode())
                .setPayStatus(OrderStatusEnum.WAIT_PAY.getCode())
                .setPayType(Optional.ofNullable(req.getPayType()).orElse(0))
                .setGoodsAmount(skuTotalAmount)
                .setDiscountAmount(discountAmount)
                .setTotalAmount(skuTotalAmount)
                .setPayAmount(payAmount)
                .setDeliveryAddr("堂食 - 桌台号：" + req.getTableNo())
                .setCouponId(req.getCouponId())
                .setRemark(req.getRemark()).setTableNo(req.getTableNo());
    }

    /**
     * 生成订单编号
     */
    private String generateOrderNo(Long storeId, Long userId, String tableNo) {
        return OrderNoGeneratorUtil.generateOrderNo(storeId, userId, tableNo);
    }
}
