package com.sweet.service.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Data
@Accessors(chain = true)
@TableName(schema = "app", value = "product_attribute_relation")
public class ProductAttributeRelation {

    @TableId(type = IdType.AUTO)
    @Schema(description = "关联 ID（自增主键）")
    private Long id;

    @Schema(description = "商品 ID（关联 product 表）")
    private Long productId;

    @Schema(description = "属性模板 ID（关联 attribute 表）")
    private Long attributeId;

    @Schema(description = "属性值 ID（关联 attribute_value 表）")
    private Long attributeValueId;

    @Schema(description = "是否必填（0=否，1=是）")
    private Integer required = 0;

    @Schema(description = "排序序号")
    private Integer sort = 0;

    @Schema(description = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime createTime;

    @Schema(description = "更新时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime updateTime;

    @Schema(description = "创建人（用户名/ID）")
    @TableField(fill = FieldFill.INSERT)
    private String createBy;

    @Schema(description = "更新人（用户名/ID）")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private String updateBy;
}
