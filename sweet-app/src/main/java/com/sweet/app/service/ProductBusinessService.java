package com.sweet.app.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sweet.app.dto.OrderCreateReqDto;
import com.sweet.app.vo.OrderCreateVo;
import com.sweet.service.entity.OrderMain;
import com.sweet.service.entity.ProductSku;
import com.sweet.service.vo.ProductSkuQueryVo;
import com.sweet.service.vo.ProductWithAttributeVo;
import com.sweet.service.vo.ProductWithCategoryVo;

import java.util.List;

/**
 * 商品业务服务接口（小程序端）
 */
public interface ProductBusinessService {

    /**
     * 根据门店 ID 获取商品分类和对应分类的商品列表
     * @param storeId 门店 ID
     * @param productName 商品名称（可选，用于模糊搜索）
     * @return 商品分类及商品列表
     */
    List<ProductWithCategoryVo> getProductsWithCategories(Long storeId, String productName);

    /**
     * 根据商品 ID 获取商品信息和商品规格信息
     * @param productId 商品 ID
     * @return 商品信息及属性信息
     */
    ProductWithAttributeVo getProductWithAttributeInfo(Long productId);

    /**
     * 根据选择中的规格获取 SKU 信息
     * @param productSkuQueryVo 查询参数
     * @return SKU 信息
     */
    ProductSku getSkuInfoByAttrs(ProductSkuQueryVo productSkuQueryVo);

    /**
     * 创建订单（扫码点餐）
     * @param req 订单创建请求
     * @return 订单创建响应
     */
    OrderCreateVo createOrder(OrderCreateReqDto req);

    /**
     * 取消订单
     * @param orderNo 订单号
     * @param userId 用户 ID
      * @param cancelReason 取消原因
     * @return 是否取消成功
     */
    boolean cancelOrder(String orderNo, Long userId, String cancelReason);
}
