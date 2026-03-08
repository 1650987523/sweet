package com.sweet.service.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sweet.service.entity.ProductSku;

import java.util.List;

public interface ProductSkuService extends BaseService<ProductSku>{

    /**
     * 获取商品SKU分页列表
     *
     * @param pageNo 页码
     * @param pageSize 每页大小
     * @param productId 商品ID
     * @return 分页结果
     */
    Page<ProductSku> getPage(Integer pageNo, Integer pageSize, Long productId);

    /**
     * 根据商品 ID 获取 SKU 列表
     *
     * @param productId 商品 ID
     * @return SKU 列表
     */
    List<ProductSku> getByProductId(Long productId);

    /**
     * 根据商品 ID 删除 SKU
     *
     * @param productId 商品 ID
     * @return 是否删除成功
     */
    boolean removeByProductId(Long productId);

    /**
     * 根据商品 ID 获取 SKU ID 列表
     *
     * @param productId 商品 ID
     * @return SKU ID 列表
     */
    List<Long> getSkuIdsByProductId(Long productId);
}