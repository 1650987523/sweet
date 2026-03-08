package com.sweet.service.service.impl;

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
import java.util.Optional;

@Slf4j
@Service
@AllArgsConstructor
public class ProductSkuServiceImpl extends BaseServiceImpl<ProductSkuMapper, ProductSku> implements ProductSkuService {

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
        return Optional.ofNullable(productSkus).orElse(List.of()).stream().map(ProductSku::getId).toList();
    }
}