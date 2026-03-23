package com.sweet.service.dto;

import com.sweet.service.entity.TableQrcode;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.web.multipart.MultipartFile;

@Data
@Accessors(chain = true)
@Schema(description = "桌码 DTO")
public class TableQrcodeDto extends TableQrcode {

    @Schema(description = "桌码图片文件")
    private MultipartFile qrcodeFile;
}
