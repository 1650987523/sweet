package com.sweet.common.enums;

public enum MenuTypeEnum {
    CATALOG("C", "目录"),
    MENU("M", "菜单"),
    FUNCTION_BUTTON("F", "功能按钮");

    private String code;
    private String desc;

    MenuTypeEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static MenuTypeEnum getMenuTypeEnum(String code) {
        for (MenuTypeEnum menuType : MenuTypeEnum.values()) {
            if (menuType.code.equals(code)) {
                return menuType;
            }
        }
        return null;
    }

    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
