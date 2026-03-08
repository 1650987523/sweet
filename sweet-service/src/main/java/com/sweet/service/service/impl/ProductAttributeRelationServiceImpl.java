package com.sweet.service.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.sweet.service.entity.ProductAttributeRelation;
import com.sweet.service.mapper.ProductAttributeRelationMapper;
import com.sweet.service.service.ProductAttributeRelationService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.Objects;

/**
 * 商品属性关联 Service 实现类
 */
@Slf4j
@Service
@AllArgsConstructor
public class ProductAttributeRelationServiceImpl extends BaseServiceImpl<ProductAttributeRelationMapper, ProductAttributeRelation> implements ProductAttributeRelationService {

    @Override
    public List<ProductAttributeRelation> getByProductId(Long productId) {
        if (ObjectUtils.isEmpty(productId)) {
            return List.of();
        }
        return super.list(Wrappers.<ProductAttributeRelation>lambdaQuery()
                .eq(ProductAttributeRelation::getProductId, productId)
                .orderByAsc(ProductAttributeRelation::getSort, ProductAttributeRelation::getId));
    }

    @Override
    public boolean removeByProductId(Long productId) {
        if (Objects.isNull(productId)) {
            return false;
        }
        return super.remove(Wrappers.<ProductAttributeRelation>lambdaQuery()
                .eq(ProductAttributeRelation::getProductId, productId));
    }

    @Override
    public boolean removeBatchByIds(List<Long> ids) {
        if (ObjectUtils.isEmpty(ids)) {
            return false;
        }
        return super.removeByIds(ids);
    }
}
