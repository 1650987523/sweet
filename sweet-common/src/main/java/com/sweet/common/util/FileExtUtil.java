package com.sweet.common.util;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;

/**
 * 文件扩展名工具类
 */
public class FileExtUtil {

    /**
     * 默认扩展名
     */
    private static final String DEFAULT_EXTENSION = "png";

    /**
     * 获取文件扩展名
     *
     * @param fileName 文件名
     * @return 扩展名（小写），如果文件名为空或未包含扩展名则返回默认扩展名
     */
    public static String getExtension(String fileName) {
        if (StrUtil.isEmpty(fileName)) {
            return DEFAULT_EXTENSION;
        }
        return StrUtil.emptyToDefault(FileUtil.extName(fileName), DEFAULT_EXTENSION).toLowerCase();
    }

    /**
     * 获取文件扩展名（带默认值）
     *
     * @param fileName 文件名
     * @param defaultExt 默认扩展名
     * @return 扩展名（小写），如果文件名为空或未包含扩展名则返回指定默认扩展名
     */
    public static String getExtension(String fileName, String defaultExt) {
        if (StrUtil.isEmpty(fileName)) {
            return defaultExt;
        }
        return StrUtil.emptyToDefault(FileUtil.extName(fileName), defaultExt).toLowerCase();
    }
}