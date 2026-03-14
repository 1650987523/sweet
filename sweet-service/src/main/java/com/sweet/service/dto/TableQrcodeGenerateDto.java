package com.sweet.service.dto;

import cn.binarywang.wx.miniapp.bean.WxMaCodeLineColor;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

import java.awt.Color;

/**
 * 微信小程序获取无限量二维码请求参数 DTO
 * 参考文档：https://developers.weixin.qq.com/miniprogram/dev/server/API/qrcode-link/qr-code/api_getunlimitedqrcode.html
 */
@Data
@Accessors(chain = true)
@Schema(description = "微信小程序二维码生成参数")
public class TableQrcodeGenerateDto {

    @Schema(description = "场景值，最大 32 个可见字符，只支持数字、大小写英文以及部分特殊字符", requiredMode = Schema.RequiredMode.REQUIRED, example = "table_001")
    private String scene;

    @Schema(description = "小程序页面路径，不能带参数，默认首页", example = "pages/index/index")
    private String page;

    @Schema(description = "是否检查 page 是否存在，为 true 时如果 page 不存在会返回错误", defaultValue = "true")
    private Boolean checkPath;

    @Schema(description = "小程序版本：release-正式版，trial-体验版，develop-开发版", defaultValue = "develop", allowableValues = {"release", "trial", "develop"})
    private String envVersion;

    @Schema(description = "二维码宽度，单位 px，最小 280，最大 1280", defaultValue = "430", minimum = "280", maximum = "1280")
    private Integer width;

    @Schema(description = "是否自动配置线条颜色，为 false 时使用 lineColor 指定颜色", defaultValue = "true")
    private Boolean autoColor;

    @Schema(description = "线条颜色 RGB 值，auto_color 为 false 时生效", defaultValue = "rgb(0, 0, 0)")
    private WxMaCodeLineColor lineColor;

    @Schema(description = "是否透明底色，为 true 时 lineColor 无效", defaultValue = "true")
    private Boolean isHyaline;

    @Schema(description = "门店ID")
    private Long storeId;

    @Schema(description = "桌码编号")
    private String qrcodeNo;
}
