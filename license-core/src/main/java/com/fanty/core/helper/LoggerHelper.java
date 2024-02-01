package com.fanty.core.helper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>日志输出辅助类</p>
 *
 * @author zhaosh
 * @date 2024/02/01
 */
public class LoggerHelper {

    /** 记录 */
    private static Logger logger = LoggerFactory.getLogger(LoggerHelper.class);

    /**
     * 记录器助手
     */
    public LoggerHelper() {
    }

    /**
     * 信息
     *
     * @param message 消息
     */
    public static void info(String message) {
        logger.info(message);
    }

    /**
     * 调试
     *
     * @param message 消息
     */
    public static void debug(String message) {
        logger.debug(message);
    }

    /**
     * 错误
     *
     * @param message 消息
     * @param ex      前任
     */
    public static void error(String message, Exception ex) {
        logger.error(message, ex);
    }

    /**
     * 错误
     *
     * @param errCode 错误代码
     * @param message 消息
     */
    public static void error(Integer errCode, String message) {
        logger.error("错误码：" + errCode + "，错误消息：" + message);
    }

    /**
     * 错误
     *
     * @param message 消息
     */
    public static void error(String message) {
        logger.error("错误消息：" + message);
    }

    /**
     * 错误
     *
     * @param errCode 错误代码
     * @param message 消息
     * @param ex      前任
     */
    public static void error(Integer errCode, String message, Exception ex) {
        logger.error("错误码：" + errCode + "，错误消息：" + message + ",异常信息：" + ex.getMessage());
    }
}