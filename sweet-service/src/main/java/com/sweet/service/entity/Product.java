package com.sweet.service.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Data
@Accessors(chain = true)
@TableName(schema = "app", value = "product", autoResultMap = true)
public class Product {

    @TableId(type = IdType.AUTO)
    @Schema(description = "商品 ID（自增主键）")
    private Long id;

    @Schema(description = "店铺 ID")
    private Long storeId;

    @Schema(description = "商品名称")
    private String productName;

    @Schema(description = "商品副标题（简短描述）")
    private String subTitle;

    @Schema(description = "分类 ID")
    private Long categoryId;

    @Schema(description = "商品详细描述")
    private String description;

    @Schema(description = "售价（单位：分）")
    private Long price;

    @Schema(description = "市场价（单位：分）")
    private Long marketPrice;

    @Schema(description = "会员价（单位：分）")
    private Long memberPrice;

    @Schema(description = "成本价（单位：分）")
    private Long costPrice;

    @Schema(description = "库存数量")
    private Integer stock;

    @Schema(description = "销量")
    private Integer sales;

    @Schema(description = "计量单位")
    private String unit;

    @Schema(description = "商品图片列表（JSONB）")
    @TableField(typeHandler = JacksonTypeHandler.class)
    private List<Map<String, Object>> images;

    @Schema(description = "轮播图列表（JSONB）")
    @TableField(typeHandler = JacksonTypeHandler.class)
    private List<Map<String, Object>> sliderImages;

    @Schema(description = "商品详细描述（Markdown）")
    private String detail;

    @Schema(description = "商品标签（JSON 数组）")
    @TableField(typeHandler = JacksonTypeHandler.class)
    private List<String> tags;

    @Schema(description = "排序序号（数字越小越靠前）")
    private Integer sort;

    @Schema(description = "是否热卖（0=否，1=是）")
    private Boolean isHot;

    @Schema(description = "是否推荐（0=否，1=是）")
    private Boolean isRecommend;

    @Schema(description = "是否新品（0=否，1=是）")
    private Boolean isNew;

    @Schema(description = "商品状态（0=禁用，1=正常，2=下架）")
    private Integer status = 1;

    @Schema(description = "删除时间（软删除）")
    private LocalDateTime deleteTime;

    @Schema(description = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @Schema(description = "更新时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @Schema(description = "创建人（用户名/ID）")
    @TableField(fill = FieldFill.INSERT)
    private String createBy;

    @Schema(description = "更新人（用户名/ID）")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private String updateBy;
}
