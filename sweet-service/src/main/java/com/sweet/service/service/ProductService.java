package com.sweet.service.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sweet.service.dto.ProductStatusDto;
import com.sweet.service.entity.Product;
import com.sweet.service.vo.ProductSimpleVo;

import java.util.List;

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
     * 根据门店 ID 和分类 ID 获取商品简列
     *
     * @param storeId 门店 ID（可选）
     * @param categoryId 分类 ID（可选）
     * @return 商品简列
     */
    List<ProductSimpleVo> getProductSimpleList(Long storeId, Long categoryId);

    /**
     * 修改商品状态
     * @return
     */
    Boolean updateStatus(ProductStatusDto dto);

    /**
     * 获取应用商品列表
     * @param storeId
     * @param categoryIds
     * @param productName
     * @return
     */
    List<Product> getAppProducts(Long storeId, List<Long> categoryIds, String productName);
}
