package com.fanty.core.ex;


import com.fanty.core.result.ResponseResult;
import com.fanty.core.result.ResultCode;
import org.springframework.http.HttpStatus;

/**
 * <p>自定义通用异常类</p>
 *
 * @author fanty
 * @version v1.0.0
 * @blob https://blog.csdn.net/fanty
 * @date created on 上午 10:59 2019-4-27
 */
public class CommonException extends Exception {

    /** 结果状态码 */
    private ResultCode resultCode;

    /** 结果消息 */
    private String message;

    /**
     * 常见异常
     *
     * @param message 消息
     */
    public CommonException(String message) {
        super(message);
        this.resultCode = ResultCode.INTERNAL;
        this.message = message;
    }

    /**
     * 常见异常
     *
     * @param resultCode 结果代码
     * @param message    消息
     */
    public CommonException(ResultCode resultCode, String message) {
        super(message);
        this.resultCode = resultCode;
        this.message = message;
    }

    /**
     * 结果错误
     *
     * @author zhaosh
     * @date 2024/02/01
     */
    public class ResultError {

        /** 错误码 */
        private Integer code;

        /** 错误名称 */
        private String name;

        /**
         * 结果错误
         *
         * @param code 法典
         * @param name 名字
         */
        public ResultError(Integer code, String name) {
            this.code = code;
            this.name = name;
        }

        /**
         * 获取代码
         *
         * @return {@link Integer}
         */
        public Integer getCode() {
            return code;
        }

        /**
         * 设置代码
         *
         * @param code 法典
         */
        public void setCode(Integer code) {
            this.code = code;
        }

        /**
         * 获取名称
         *
         * @return {@link String}
         */
        public String getName() {
            return name;
        }

        /**
         * 设置名称
         *
         * @param name 名字
         */
        public void setName(String name) {
            this.name = name;
        }
    }

    /**
     * 构建结果
     *
     * @return {@link ResponseResult}
     */
    public ResponseResult buildResult() {
        Integer code = this.resultCode.getCode();
        String name = this.resultCode.getName();
        return new ResponseResult(HttpStatus.BAD_REQUEST.value(), this.message, new ResultError(code, name));
    }
}