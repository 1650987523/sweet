package com.sweet.common.enums;

public enum BannerStatusEnum {

    ONLINE(1, "上架"),
    OFFLINE(0, "下架");

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
