package com.sweet.service.dto;

import com.sweet.service.entity.Banner;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
@Schema(description = "轮播图批量保存 DTO")
public class BannerBatchSaveDto extends Banner {

    @Schema(description = "图片URL列表")
    private List<String> imageUrls;
}
