package com.sweet.service.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.sweet.service.entity.ProductSkuAttributeRelation;
import com.sweet.service.mapper.ProductSkuAttributeRelationMapper;
import com.sweet.service.service.ProductSkuAttributeRelationService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.util.List;

/**
 * 商品 SKU 属性关联 Service 实现类
 */
@Slf4j
@Service
@AllArgsConstructor
public class ProductSkuAttributeRelationServiceImpl extends BaseServiceImpl<ProductSkuAttributeRelationMapper, ProductSkuAttributeRelation> implements ProductSkuAttributeRelationService {

    @Override
    public List<ProductSkuAttributeRelation> getBySkuId(Long skuId) {
        return super.list(Wrappers.<ProductSkuAttributeRelation>lambdaQuery()
                .eq(ProductSkuAttributeRelation::getSkuId, skuId)
                .orderByAsc(ProductSkuAttributeRelation::getSort, ProductSkuAttributeRelation::getId));
    }

    @Override
    public boolean removeBySkuId(Long skuId) {
        return super.remove(Wrappers.<ProductSkuAttributeRelation>lambdaQuery()
                .eq(ProductSkuAttributeRelation::getSkuId, skuId));
    }

    @Override
    public boolean removeBySkuIds(List<Long> skuIds) {
        if(CollectionUtils.isEmpty(skuIds)){
            return true;
        }
        return super.remove(Wrappers.<ProductSkuAttributeRelation>lambdaQuery()
                .in(ProductSkuAttributeRelation::getSkuId, skuIds));
    }

    @Override
    public boolean removeBatchByIds(List<Long> ids) {
        if (ObjectUtils.isEmpty(ids)) {
            return false;
        }
        return super.removeByIds(ids);
    }
}
