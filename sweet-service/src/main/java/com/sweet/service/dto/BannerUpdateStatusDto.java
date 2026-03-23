package com.sweet.service.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "轮播图状态更新 DTO")
public class BannerUpdateStatusDto {

    private Long id;
    private Integer status;
}
