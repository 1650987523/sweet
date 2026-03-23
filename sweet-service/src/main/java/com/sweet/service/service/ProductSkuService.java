package com.sweet.service.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sweet.service.entity.ProductSku;

import java.util.List;

public interface ProductSkuService extends BaseService<ProductSku> {

    /**
     * 获取商品 SKU 分页列表
     *
     * @param pageNo 页码
     * @param pageSize 每页大小
     * @param productId 商品 ID
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

    /**
     * 根据匹配的 SKU ID 和商品 ID 获取 SKU
     * @param id
     * @param productId
     * @return
     */
    ProductSku getSku(Long id, Long productId);

    /**
     * 扣减库存
     * <p>
     * 注意：调用前需确保已通过 selectForUpdate 获取行锁
     *
     * @param skuId SKU ID
     * @param quantity 扣减数量
     * @return 是否成功
     */
    boolean reduceStock(Long skuId, Integer quantity);

    /**
     * 查询库存（行锁）
     * @param skuId SKU ID
     * @return SKU 信息
     */
    ProductSku selectForUpdate(Long skuId);

    /**
     * 根据 SKU ID 列表批量查询 SKU
     * @param skuIds SKU ID 列表
     * @return SKU 列表
     */
    List<ProductSku> getByIds(List<Long> skuIds);

    /**
     * 恢复库存（行锁）
     * @param skuId SKU ID
     * @param quantity 恢复数量
     */
    void addStock(Long skuId, Integer quantity);
}