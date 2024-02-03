package com.fanty.core.ex;

import com.fanty.core.result.ResponseResult;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

/**
 * <p>全局接口异常拦截器</p>
 *
 * @author zhaosh
 * @date 2024/02/01
 */
@CrossOrigin
@ControllerAdvice
@RestControllerAdvice
public class ExceptionControllerAdvice {
    /**
     * 异常控制器建议
     */
    public ExceptionControllerAdvice() {
    }

    /**
     * 初始化活页夹
     *
     * @param binder 粘结 剂
     */
    @InitBinder
    public void initBinder(WebDataBinder binder) {
    }

    /**
     * 添加属性
     *
     * @param model 型
     */
    @ModelAttribute
    public void addAttributes(Model model) {
    }

    /**
     * 错误处理程序
     *
     * @param ex 异常
     * @return {@link ResponseEntity}
     */
    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public ResponseEntity errorHandler(Exception ex) {
        if (ex instanceof CommonException) {
            CommonException commonException = (CommonException) ex;
            return ResponseEntity.ok(commonException.buildResult());
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ResponseResult.fail(ex.getMessage()));
    }
}