package com.sweet.service.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sweet.service.dto.ProductStatusDto;
import com.sweet.service.entity.Product;

public interface ProductService extends BaseService<Product>{

    /**
     * 获取商品分页列表
     *
     * @param pageNo 页码
     * @param pageSize 每页大小
     * @param storeId 门店 ID
     * @param categoryId 分类 ID
     * @param productName 商品名称
     * @param status 商品状态
     * @param isHot 是否热卖
     * @param isRecommend 是否推荐
     * @param isNew 是否新品
     * @return 分页结果
     */
    Page<Product> getPage(Integer pageNo, Integer pageSize, Long storeId, Long categoryId,
                          String productName, Integer status, Boolean isHot,
                          Boolean isRecommend, Boolean isNew);

    /**
     * 修改商品状态
     * @return
     */
    Boolean updateStatus(ProductStatusDto dto);
}
