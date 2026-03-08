package com.sweet.service.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sweet.service.entity.ProductSkuAttributeRelation;

import java.util.List;

/**
 * 商品 SKU 属性关联 Service 接口
 */
public interface ProductSkuAttributeRelationService extends IService<ProductSkuAttributeRelation> {

    /**
     * 根据 SKU ID 获取属性关联列表
     *
     * @param skuId SKU ID
     * @return 属性关联列表
     */
    List<ProductSkuAttributeRelation> getBySkuId(Long skuId);

    /**
     * 根据 SKU ID 删除属性关联
     *
     * @param skuId SKU ID
     */
    boolean removeBySkuId(Long skuId);

    /**
     * 根据 SKU ID 列表批量删除属性关联
     *
     * @param skuIds SKU ID 列表
     */
    boolean removeBySkuIds(List<Long> skuIds);

    /**
     * 根据 ID 列表批量删除属性关联
     *
     * @param ids ID 列表
     * @return 是否删除成功
     */
    boolean removeBatchByIds(List<Long> ids);
}
