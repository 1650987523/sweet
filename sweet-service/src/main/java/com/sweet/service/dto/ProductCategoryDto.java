package com.sweet.service.dto;

import com.sweet.service.entity.ProductCategory;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.web.multipart.MultipartFile;

@Data
@Accessors(chain = true)
@Schema(description = "商品分类 DTO")
public class ProductCategoryDto extends ProductCategory {

    @Schema(description = "分类图标文件")
    private MultipartFile iconFile;
}
