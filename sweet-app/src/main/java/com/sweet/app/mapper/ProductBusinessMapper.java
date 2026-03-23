package com.sweet.app.mapper;

import com.sweet.app.vo.SelectSkuVo;
import com.sweet.service.mapper.BaseMapper;
import com.sweet.service.vo.ProductSkuQueryVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProductBusinessMapper extends BaseMapper {

    /**
     * 根据规格列表和商品 ID 获取匹配的 SKU ID
     * @param skuAttrs 规格列表
     * @param productId 商品 ID
     * @return 匹配的 SKU ID
     */
    SelectSkuVo selectSkuIdByAttrs(@Param("skuAttrs") List<ProductSkuQueryVo.SelectedSpecVo> skuAttrs,
                            @Param("productId") Long productId, @Param("count") int count);
}