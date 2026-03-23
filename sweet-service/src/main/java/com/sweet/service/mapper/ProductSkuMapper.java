package com.sweet.service.mapper;

import com.sweet.service.entity.ProductSku;
import org.apache.ibatis.annotations.Param;

public interface ProductSkuMapper extends BaseMapper<ProductSku> {

    /**
     * 扣减库存
     * <p>
     * 注意：调用前需确保已通过 selectForUpdate 获取行锁
     * @param skuId SKU ID
     * @param quantity 扣减数量
     * @return 影响行数
     */
    int reduceStock(@Param("skuId") Long skuId, @Param("quantity") Integer quantity);

    /**
     * 查询库存（行锁）
     * @param skuId SKU ID
     * @return SKU 信息
     */
    ProductSku selectForUpdate(@Param("skuId") Long skuId);

    /**
     * 恢复库存（行锁）
     * @param skuId SKU ID
     * @param quantity 恢复数量
     * @return 影响行数
     */
    int addStock(@Param("skuId") Long skuId, @Param("quantity") Integer quantity);
}