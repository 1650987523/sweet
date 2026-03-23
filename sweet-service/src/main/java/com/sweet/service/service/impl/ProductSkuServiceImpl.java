package com.sweet.service.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sweet.service.entity.ProductSku;
import com.sweet.service.mapper.ProductSkuMapper;
import com.sweet.service.service.ProductSkuService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Slf4j
@Service
@AllArgsConstructor
public class ProductSkuServiceImpl extends BaseServiceImpl<ProductSkuMapper, ProductSku> implements ProductSkuService {

    private final ProductSkuMapper productSkuMapper;

    @Override
    public Page<ProductSku> getPage(Integer pageNo, Integer pageSize, Long productId) {
        Page<ProductSku> page = new Page<>(pageNo, pageSize);

        return super.page(page, Wrappers.<ProductSku>lambdaQuery()
                .eq(Objects.nonNull(productId), ProductSku::getProductId, productId));
    }

    @Override
    public List<ProductSku> getByProductId(Long productId) {
        if (Objects.isNull(productId)) {
            return List.of();
        }
        return super.list(Wrappers.<ProductSku>lambdaQuery()
                .eq(ProductSku::getProductId, productId)
                .orderByAsc(ProductSku::getId));
    }

    @Override
    public boolean removeByProductId(Long productId) {
        if(Objects.isNull(productId)) {
            return false;
        }
        return super.remove(Wrappers.<ProductSku>lambdaQuery()
                .eq(ProductSku::getProductId, productId));
    }

    @Override
    public List<Long> getSkuIdsByProductId(Long productId) {
        List<ProductSku> productSkus = this.getByProductId(productId);
        return productSkus.stream().map(ProductSku::getId).toList();
    }

    @Override
    public ProductSku getSku(Long id, Long productId) {
        QueryWrapper<ProductSku> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(ProductSku::getProductId, productId)
                .eq(ProductSku::getId, id);
        return super.getOne(queryWrapper);
    }

    @Override
    public boolean reduceStock(Long skuId, Integer quantity) {
        int affected = productSkuMapper.reduceStock(skuId, quantity);
        return affected > 0;
    }

    @Override
    public ProductSku selectForUpdate(Long skuId) {
        return productSkuMapper.selectForUpdate(skuId);
    }

    @Override
    public List<ProductSku> getByIds(List<Long> skuIds) {
        if (CollectionUtils.isEmpty(skuIds)) {
            return List.of();
        }
        return super.list(Wrappers.<ProductSku>lambdaQuery()
                .in(ProductSku::getId, skuIds));
    }

    @Override
    public void addStock(Long skuId, Integer quantity) {
        productSkuMapper.addStock(skuId, quantity);
    }
}
