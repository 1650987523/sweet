package com.sweet.common.util;

import cn.hutool.core.date.DatePattern;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static com.sweet.common.constant.AdminConstant.DATE_TIME_FORMATTER;
import static com.sweet.common.constant.AdminConstant.ZONE_ID;

public class DateFormatUtil {

    /**
     * 格式化时间戳
     *
     * @param milliTimestamp
     * @return
     */
    public static String formatMilliTimestamp(long milliTimestamp) {
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(milliTimestamp), ZONE_ID)
                .format(DATE_TIME_FORMATTER);
    }

    /**
     * 格式化【毫秒级】时间戳
     *
     * @param milliTimestamp
     * @param pattern
     * @return
     */
    public static String formatMilliTimestamp(long milliTimestamp, String pattern) {
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(milliTimestamp), ZONE_ID)
                .format(DateTimeFormatter.ofPattern(pattern));
    }

    /**
     * 格式化【秒级】时间戳（如 Sa-Token 的 StpUtil.getTokenTimeout()）
     */
    public static String formatSecondTimestamp(long secondTimestamp) {
        return LocalDateTime.ofInstant(Instant.ofEpochSecond(secondTimestamp), ZONE_ID)
                .format(DATE_TIME_FORMATTER);
    }

    /**
     * 格式化【秒级】时间戳
     *
     * @param secondTimestamp
     * @param pattern
     * @return
     */
    public static String formatSecondTimestamp(long secondTimestamp, String pattern) {
        return LocalDateTime.ofInstant(Instant.ofEpochSecond(secondTimestamp), ZONE_ID)
                .format(DateTimeFormatter.ofPattern(pattern));
    }

    /**
     * 增加秒数
     *
     * @param offsetSeconds
     * @return
     */
    public static String offsetSecond(Long offsetSeconds) {
        LocalDateTime currentTime = LocalDateTime.now(ZONE_ID);
        LocalDateTime offsetTime = currentTime.plusSeconds(offsetSeconds);
        DateTimeFormatter customFormatter = DateTimeFormatter.ofPattern(DatePattern.NORM_DATETIME_PATTERN);
        return offsetTime.format(customFormatter);
    }

    /**
     * 增加秒数
     *
     * @param offsetSeconds
     * @return
     */
    public static String offsetSecond(Long offsetSeconds, String pattern) {
        LocalDateTime currentTime = LocalDateTime.now(ZONE_ID);
        LocalDateTime offsetTime = currentTime.plusSeconds(offsetSeconds);
        DateTimeFormatter customFormatter = DateTimeFormatter.ofPattern(pattern);
        return offsetTime.format(customFormatter);
    }

    /**
     * 增加分钟数
     *
     * @param offsetMinutes
     * @return
     */
    public static String offsetMinute(Long offsetMinutes) {
        LocalDateTime currentTime = LocalDateTime.now(ZONE_ID);
        LocalDateTime offsetTime = currentTime.plusMinutes(offsetMinutes);
        DateTimeFormatter customFormatter = DateTimeFormatter.ofPattern(DatePattern.NORM_DATETIME_PATTERN);
        return offsetTime.format(customFormatter);
    }
}
