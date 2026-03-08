package com.sweet.admin.service;

import com.sweet.service.dto.ProductDTO;
import com.sweet.service.dto.ProductUploadDto;
import com.sweet.service.vo.ProductDetailWithRelationsVO;

import java.util.List;

/**
 * 商品业务 Service 接口
 */
public interface ProductBusinessService {

    /**
     * 创建商品（包含 SKU、属性关联）
     *
     * @param dto 商品 DTO
     * @return 商品 ID
     */
    Long createProduct(ProductDTO dto);

    /**
     * 更新商品（全量替换 SKU、属性关联）
     *
     * @param dto 商品 DTO（包含商品 ID）
     * @return 是否更新成功
     */
    Boolean updateProduct(ProductDTO dto);

    /**
     * 上传商品图片
     *
     * @param dto 商品图片上传 DTO
     * @return 图片 URL 列表
     */
    List<String> uploadProductImages(ProductUploadDto dto);

    /**
     * 删除商品图片
     *
     * @param imageUrls 图片 URL 列表
     * @return 是否删除成功
     */
    Boolean deleteProductImages(List<String> imageUrls);

    /**
     * 根据商品 ID 获取商品详情（包含属性关联、SKU 及 SKU 属性关联）
     *
     * @param productId 商品 ID
     * @return 商品详情 VO
     */
    ProductDetailWithRelationsVO getProductDetailById(Long productId);

    /**
     * 根据商品 ID 删除商品（级联删除属性关联、SKU 及 SKU 属性关联）
     *
     * @param productId 商品 ID
     * @return 是否删除成功
     */
    Boolean deleteProductById(Long productId);
}
