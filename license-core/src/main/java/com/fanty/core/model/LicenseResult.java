package com.fanty.core.model;

import de.schlichtherle.license.LicenseContent;

/**
 * <p>License证书验证结果对象</p>
 *
 * @author zhaosh
 * @date 2024/02/01
 */
public class LicenseResult {

    /** 检验结果 */
    private Boolean result;
    /** 附加信息 */
    private String message;
    /** 证书内容 */
    private LicenseContent content;
    /** 检验失败错误 */
    private Exception exception;

    /**
     * 许可证结果
     *
     * @param content 内容
     */
    public LicenseResult(LicenseContent content) {
        this.result = true;
        this.content = content;
    }

    /**
     * 许可证结果
     *
     * @param message 消息
     * @param content 内容
     */
    public LicenseResult(String message, LicenseContent content) {
        this.result = true;
        this.message = message;
        this.content = content;
    }

    /**
     * 许可证结果
     *
     * @param exception 例外
     */
    public LicenseResult(Exception exception) {
        this.result = false;
        this.exception = exception;
    }

    /**
     * 许可证结果
     *
     * @param message   消息
     * @param exception 例外
     */
    public LicenseResult(String message, Exception exception) {
        this.result = false;
        this.message = message;
        this.exception = exception;
    }

    /**
     * 许可证结果
     *
     * @param result    结果
     * @param message   消息
     * @param exception 例外
     */
    public LicenseResult(boolean result, String message, Exception exception) {
        this.result = result;
        this.message = message;
        this.exception = exception;
    }

    public Boolean getResult() {
        return result;
    }

    public void setResult(Boolean result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LicenseContent getContent() {
        return content;
    }

    public void setContent(LicenseContent content) {
        this.content = content;
    }

    public Exception getException() {
        return exception;
    }

    public void setException(Exception exception) {
        this.exception = exception;
    }
}