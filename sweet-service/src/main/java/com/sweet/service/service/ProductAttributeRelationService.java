package com.sweet.service.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sweet.service.entity.ProductAttributeRelation;

import java.util.List;

/**
 * 商品属性关联 Service 接口
 */
public interface ProductAttributeRelationService extends IService<ProductAttributeRelation> {

    /**
     * 根据商品 ID 获取属性关联列表
     *
     * @param productId 商品 ID
     * @return 属性关联列表
     */
    List<ProductAttributeRelation> getByProductId(Long productId);

    /**
     * 根据商品 ID 删除属性关联
     *
     * @param productId 商品 ID
     */
    boolean removeByProductId(Long productId);

    /**
     * 根据 ID 列表批量删除属性关联
     *
     * @param ids ID 列表
     * @return 是否删除成功
     */
    boolean removeBatchByIds(List<Long> ids);
}
