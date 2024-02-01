package com.fanty.core.result;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import org.springframework.http.HttpStatus;

import java.util.Date;

/**
 * <p>请求结果（封装）</p>
 *
 * @author zhaosh
 * @date 2024/02/01
 */
public class ResponseResult {

    /** 响应结果状态码 */
    private Integer status;

    /** 响应结果消息 */
    private String message;

    /** 响应结果对应的（包含）的数据，空的话不反序列话 */
    @JsonInclude(value = Include.NON_NULL)
    private Object data;

    /** 响应时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date timeStamp = new Date();

    /**
     * 响应结果
     */
    public ResponseResult() {
        this.status = null;
        this.message = null;
        this.data = null;
    }

    /**
     * 响应结果
     *
     * @param status  地位
     * @param message 消息
     */
    public ResponseResult(Integer status, String message) {
        this.status = status;
        this.message = message;
        this.data = null;
    }

    /**
     * 响应结果
     *
     * @param status  地位
     * @param message 消息
     * @param data    数据
     */
    public ResponseResult(Integer status, String message, Object data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    /**
     * 响应结果
     *
     * @param resultCode 结果代码
     * @param message    消息
     */
    public ResponseResult(ResultCode resultCode, String message) {
        this.status = resultCode.getCode();
        this.message = message;
    }

    /**
     * 默认成功返回的实例
     *
     * @param data 对象
     */
    private ResponseResult(Object data) {
        this.status = ResultCode.OK.getCode();
        this.message = ResultCode.OK.getName();
        this.data = data;
    }

    /**
     * 响应结果
     *
     * @param message 消息
     * @param data    数据
     */
    private ResponseResult(String message, Object data) {
        this.status = ResultCode.OK.getCode();
        this.message = message;
        this.data = data;
    }

    /**
     * 响应结果
     *
     * @param code 法典
     */
    private ResponseResult(ResultCode code) {
        this.status = code.getCode();
        this.message = code.getName();
        this.data = null;
    }

    public Integer getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public Object getData() {
        return data;
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

    public static ResponseResult ok(Object data) {
        return new ResponseResult(data);
    }

    public static ResponseResult ok(String message, Object data) {
        return new ResponseResult(data);
    }

    public static ResponseResult fail(ResultCode code) {
        return new ResponseResult(code);
    }

    public static ResponseResult fail(String message) {
        return new ResponseResult(HttpStatus.BAD_REQUEST.value(), "失败", message);
    }
}