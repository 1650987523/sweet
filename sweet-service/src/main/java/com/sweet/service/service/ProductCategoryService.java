package com.sweet.service.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sweet.service.dto.ProductCategoryDto;
import com.sweet.service.entity.ProductCategory;
import com.sweet.service.vo.ProductCategoryNodeVo;
import com.sweet.service.vo.ProductCategoryOptionVo;

import java.util.List;

public interface ProductCategoryService extends BaseService<ProductCategory>{

    /**
     * 获取商品分类分页列表
     *
     * @param pageNo 页码
     * @param pageSize 每页大小
     * @param storeId 门店 ID
     * @param categoryName 分类名称
     * @param status 状态
     * @return 分页结果
     */
    Page<ProductCategory> getPage(Integer pageNo, Integer pageSize, Long storeId, String categoryName, Integer status);

    /**
     * 获取商品分类树形结构（按门店）
     *
     * @param storeId 门店 ID（必填）
     * @return 商品分类树
     */
    List<ProductCategoryNodeVo> getCategoryTree(Long storeId);

    /**
     * 获取商品分类选项（按门店）
     * @param storeId 门店 ID（必填）
     * @return 商品分类选项
     */
    List<ProductCategoryOptionVo> getProductCategoryOptions(Long storeId);

    /**
     * 删除商品分类（有子分类时不能删除）
     * @param id 分类 ID
     * @return 是否删除成功
     */
    boolean removeCategory(Long id);

    /**
     * 新增商品分类（支持上传图标）
     * @param dto 商品分类 DTO（包含图标文件）
     * @return 是否保存成功
     */
    boolean save(ProductCategoryDto dto);

    /**
     * 更新商品分类（支持上传图标）
     * @param dto 商品分类 DTO（包含图标文件）
     * @return 是否更新成功
     */
    boolean updateById(ProductCategoryDto dto);
}