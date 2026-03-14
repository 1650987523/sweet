package com.sweet.app.enums;

public enum BannerStatusEnum {

    ENABLE(1, "上架"),
    DISABLE(0, "下架");

    private final int code;
    private final String desc;

    BannerStatusEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public int getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
