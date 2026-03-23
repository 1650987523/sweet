package com.sweet.service.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sweet.service.dto.ProductStatusDto;
import com.sweet.service.entity.Product;
import com.sweet.service.mapper.ProductMapper;
import com.sweet.service.service.ProductService;
import com.sweet.service.vo.ProductSimpleVo;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Slf4j
@Service
@AllArgsConstructor
public class ProductServiceImpl extends BaseServiceImpl<ProductMapper, Product> implements ProductService {

    @Override
    public Page<Product> getPage(Integer pageNo, Integer pageSize, Long storeId, Long categoryId,
                                 String productName, Integer status, Boolean isHot,
                                 Boolean isRecommend, Boolean isNew) {
        Page<Product> page = new Page<>(pageNo, pageSize);

        QueryWrapper<Product> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda()
                .eq(Objects.nonNull(storeId), Product::getStoreId, storeId)
                .eq(Objects.nonNull(categoryId), Product::getCategoryId, categoryId)
                .like(StringUtils.hasText(productName), Product::getProductName, productName)
                .eq(Objects.nonNull(status), Product::getStatus, status)
                .eq(Objects.nonNull(isHot), Product::getIsHot, isHot)
                .eq(Objects.nonNull(isRecommend), Product::getIsRecommend, isRecommend)
                .eq(Objects.nonNull(isNew), Product::getIsNew, isNew)
                .orderByDesc(Product::getSort, Product::getId);

        return super.page(page, queryWrapper);
    }

    @Override
    public List<ProductSimpleVo> getProductSimpleList(Long storeId, Long categoryId) {

        //查询
        QueryWrapper<Product> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda()
                .eq(Objects.nonNull(storeId), Product::getStoreId, storeId)
                .eq(Objects.nonNull(categoryId), Product::getCategoryId, categoryId)
                .orderByDesc(Product::getSort, Product::getId);
        List<Product> products = super.list(queryWrapper);

        //返回
        return Optional.ofNullable(products).orElse(List.of()).stream()
                .map(product -> new ProductSimpleVo()
                        .setId(product.getId())
                        .setCategoryId(product.getCategoryId())
                        .setProductName(product.getProductName()))
                .toList();
    }

    @Override
    public Boolean updateStatus(ProductStatusDto dto) {

        Long id = dto.getId();
        Integer status = dto.getStatus();

        Assert.notNull(id, "商品 ID 不能为空");
        Assert.notNull(status, "商品状态 不能为空");

        UpdateWrapper<Product> updateWrapper = new UpdateWrapper<>();
        updateWrapper.lambda().eq(Product::getId, id).set(Product::getStatus, status);
        return super.update(updateWrapper);
    }

    @Override
    public List<Product> getAppProducts(Long storeId, List<Long> categoryIds, String productName) {
        QueryWrapper<Product> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda()
                .eq(Objects.nonNull(storeId), Product::getStoreId, storeId)
                .in(Objects.nonNull(categoryIds), Product::getCategoryId, categoryIds)
                .like(StringUtils.hasText(productName), Product::getProductName, productName)
                .orderByDesc(Product::getSort, Product::getId);
        return super.list(queryWrapper);
    }
}