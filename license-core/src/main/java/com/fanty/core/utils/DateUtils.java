package com.fanty.core.utils;

import com.fanty.core.ex.CommonException;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

/**
 * <p>日期工具类</p>
 *
 * @author zhaosh
 * @date 2024/02/01
 */
public class DateUtils {

    /** s 日期格式 */
    private static SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd HH_mm_ss");
    /** DTF */
    private static DateTimeFormatter dtF = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    /** 日历 */
    private static Calendar calendar = Calendar.getInstance();

    /**
     * 日期实用程序
     */
    public DateUtils() {
    }

    /**
     * 获取文件当前日期
     *
     * @return {@link String}
     */
    public static synchronized String getCurrentDateForFile() {
        long currentTimeMillis = System.currentTimeMillis();
        Date date = new Date(currentTimeMillis);
        return sDateFormat.format(date);
    }

    /**
     * 获取时间
     *
     * @param time 时间
     * @return {@link Long}
     * @throws CommonException <p>自定义通用异常类</p>
     */
    public static Long getTime(String time) throws CommonException {
        if (CommonUtils.isEmpty(time)) {
            throw new CommonException("时间[" + time + "]格式不合法");
        } else if (time.length() < 11) {
            dtF = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate parse = LocalDate.parse(time, dtF);
            return LocalDate.from(parse).atStartOfDay(ZoneId.systemDefault()).toInstant().toEpochMilli();
        } else {
            LocalDateTime parse = LocalDateTime.parse(time, dtF);
            return LocalDateTime.from(parse).atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
        }
    }

    /**
     * 获取日期
     *
     * @return {@link String}
     */
    public static synchronized String getDate() {
        Date date = new Date();
        sDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sDateFormat.format(date);
    }

    /**
     * 日期2 str
     *
     * @param time 时间
     * @return {@link String}
     */
    public static synchronized String date2Str(Long time) {
        Date date = new Date(time);
        sDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sDateFormat.format(date);
    }

    /**
     * 日期2 str
     *
     * @param time 时间
     * @return {@link String}
     */
    public static synchronized String date2Str(Date time) {
        sDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sDateFormat.format(time);
    }

    /**
     * str2 日期
     *
     * @param time 时间
     * @return {@link Date}
     * @throws CommonException <p>自定义通用异常类</p>
     */
    public static synchronized Date str2Date(String time) throws CommonException {
        sDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        try {
            return sDateFormat.parse(time);
        } catch (Exception var2) {
            throw new CommonException("字符串[" + time + "]转换日期格式异常");
        }
    }

    /**
     * 添加年份
     *
     * @param date  日期
     * @param mount 安装
     * @return {@link Date}
     */
    public static Date addYear(Date date, int mount) {
        calendar.setTime(date);
        calendar.add(1, mount);
        return calendar.getTime();
    }

    /**
     * 添加年份
     *
     * @param time  时间
     * @param mount 安装
     * @return {@link Date}
     */
    public static Date addYear(Long time, int mount) {
        Date date = new Date(time);
        calendar.setTime(date);
        calendar.add(1, mount);
        return calendar.getTime();
    }

    /**
     * 添加月份
     *
     * @param date  日期
     * @param mount 安装
     * @return {@link Date}
     */
    public static Date addMonth(Date date, int mount) {
        calendar.setTime(date);
        calendar.add(2, mount);
        return calendar.getTime();
    }

    /**
     * 添加月份
     *
     * @param time  时间
     * @param mount 安装
     * @return {@link Date}
     */
    public static Date addMonth(Long time, int mount) {
        Date date = new Date(time);
        calendar.setTime(date);
        calendar.add(1, mount);
        return calendar.getTime();
    }

    /**
     * 添加日期
     *
     * @param date  日期
     * @param mount 安装
     * @return {@link Date}
     */
    public static Date addDay(Date date, int mount) {
        calendar.setTime(date);
        calendar.add(5, mount);
        return calendar.getTime();
    }

    /**
     * 添加日期
     *
     * @param time  时间
     * @param mount 安装
     * @return {@link Date}
     */
    public static Date addDay(Long time, int mount) {
        Date date = new Date(time);
        calendar.setTime(date);
        calendar.add(5, mount);
        return calendar.getTime();
    }

    public static void main(String[] args) {
        Long time = 1555588742901L;
        date2Str(time);
    }
}