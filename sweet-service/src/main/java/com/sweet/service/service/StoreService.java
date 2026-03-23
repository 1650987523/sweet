package com.sweet.service.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sweet.service.entity.Store;

import java.util.List;

public interface StoreService extends BaseService<Store>{

    /**
     * 获取门店分页列表
     *
     * @param pageNo 页码
     * @param pageSize 每页大小
     * @param storeId 门店ID
     * @param name 门店名称
     * @return 分页结果
     */
    Page<Store> getPage(Integer pageNo, Integer pageSize,Integer storeId, String name);

    /**
     * 获取所有门店列表（用于下拉选项）
     * @param storeId 门店 ID（可选，传入则根据 ID 获取，否则获取所有）
     * @return 门店列表
     */
    List<Store> getStoresById(Integer storeId);

    /**
     * 获取所有门店列表
     * @return
     */
    List<Store> getAllStores();

    /**
     * app界面所需分页数据（用于展示门店列表）
     * @param pageNo
     * @param pageSize
     * @param storeName
     * @return
     */
    Page<Store> getAppPage(Integer pageNo, Integer pageSize, String storeName);
}
