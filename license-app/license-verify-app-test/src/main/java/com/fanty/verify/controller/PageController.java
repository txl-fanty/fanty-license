package com.fanty.verify.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * 页面控制器
 *
 * @author zhaosh
 * @date 2024/02/01
 */
@Controller
public class PageController {

    /**
     * 获取登录名
     * 测试地址：http://localhost:8081/login
     *
     * @param page 页
     * @return {@link String}
     */
    @GetMapping("/{page}")
    public String getLogin(@PathVariable(name = "page") String page) {
        return page + ".html";
    }
}