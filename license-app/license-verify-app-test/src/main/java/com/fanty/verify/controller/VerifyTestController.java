package com.fanty.verify.controller;

import com.fanty.verify.annotion.VLicense;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 验证测试控制器
 *
 * @author zhaosh
 * @date 2024/02/01
 */
@CrossOrigin
@RestController
@RequestMapping("license")
public class VerifyTestController {

    /**
     * 打招呼
     *
     * @return {@link String}
     */
    @VLicense
    @GetMapping("/hello")
    public String sayHello() {
        return "hello license !";
    }
}