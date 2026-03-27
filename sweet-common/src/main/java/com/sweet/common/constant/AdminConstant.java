package com.sweet.common.constant;

import com.sweet.common.enums.OrderStatusEnum;

import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class AdminConstant {

    public static final String DATE_PATTERN_HORIZONTAL_LINE = "yyyy/MM/dd HH:mm:ss";

    public static final DateTimeFormatter DATE_TIME_FORMATTER =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static final ZoneId ZONE_ID = ZoneId.of("Asia/Shanghai");

    /**
     * 文件上传路径前缀定义
     */
    public static final String OSS_PRODUCT_PREFIX_SPU = "product/spu/";
    public static final String OSS_PRODUCT_PREFIX_SKU = "product/sku/";
    public static final String OSS_PRODUCT_PREFIX_QRCODE = "product/qrcode/";
    public static final String OSS_PRODUCT_PREFIX_CATEGORY = "product/category/";
    public static final String OSS_PRODUCT_PREFIX_BANNER = "product/banner/";
    public static final String OSS_PRODUCT_PREFIX = "product/";
    public static final String FILE_SUFFIX_PNG = ".png";
    public static final String PATH_SEPARATOR = "/";

    /**
     * 图片 jsonb字段 相关常量
     */
    public static final String IMAGE_JSONB_KEY_URL = "url";
    public static final String IMAGE_JSONB_KEY_SORT = "sort";
    public static final String IMAGE_JSONB_KEY_DESCRIPTION = "description";
    public static final Integer DEFAULT_IMAGE_SORT = 0;

    /**
     * Sweet OSS R2 配置相关常量
     */
    public static final String OSS_CONFIG_PREFIX = "sweet.oss.s3";
    public static final String OSS_ENABLED = "enabled";
    public static final String OSS_ENABLED_VALUE = "true";

    public static final String USER_ID_KEY = "userId";
    public static final String USER_NAME_KEY = "username";

    /**
     * 可以申请退款的状态
     */
    public static final List<Integer> ORDER_STATUS_CAN_APPLY_REFUND  = List.of(
            OrderStatusEnum.IN_PROGRESS.getCode(),
            OrderStatusEnum.COMPLETED.getCode()
    );
}
