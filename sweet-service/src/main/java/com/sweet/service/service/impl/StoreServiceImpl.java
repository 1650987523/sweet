package com.sweet.service.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sweet.service.entity.Store;
import com.sweet.service.mapper.StoreMapper;
import com.sweet.service.service.StoreService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Objects;

@Slf4j
@Service
@AllArgsConstructor
public class StoreServiceImpl extends BaseServiceImpl<StoreMapper, Store> implements StoreService {
    @Override
    public Page<Store> getPage(Integer pageNo, Integer pageSize, Integer storeId, String name) {
        Page<Store> page = new Page<>(pageNo, pageSize);

        QueryWrapper<Store> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(Objects.nonNull(storeId), Store::getId, storeId);
        queryWrapper.lambda().like(StringUtils.hasText(name), Store::getName, name);

        return super.page(page, queryWrapper);
    }

    @Override
    public List<Store> getStoresById(Integer storeId) {
        QueryWrapper<Store> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(Objects.nonNull(storeId), Store::getId, storeId);
        return super.list(queryWrapper);
    }

    @Override
    public List<Store> getAllStores() {
        return super.list();
    }

    @Override
    public Page<Store> getAppPage(Integer pageNo, Integer pageSize, String storeName) {
        Page<Store> page = new Page<>(pageNo, pageSize);
        QueryWrapper<Store> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().like(StringUtils.hasText(storeName), Store::getName, storeName);
        return super.page(page, queryWrapper);
    }
}
