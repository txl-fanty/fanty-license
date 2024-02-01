package com.fanty.verify.controller;

import com.fanty.core.result.ResponseResult;
import com.fanty.verify.annotion.VLicense;
import org.springframework.web.bind.annotation.*;

/**
 * 登录测试控制器
 *
 * @author zhaosh
 * @date 2024/02/01
 */
@CrossOrigin
@RestController
@RequestMapping("/user")
public class LoginTestController {

    /**
     * 检查许可证
     *
     * @param user 用户
     * @return {@link ResponseResult}
     */
    @VLicense
    @PostMapping("/login")
    public ResponseResult checkLicense(@RequestBody User user) {
        if ("admin".equals(user.getUserName()) && "admin".equals(user.getPassword())) {
            return ResponseResult.ok("登陆成功！");
        } else {
            return ResponseResult.fail("用户名或密码不对！");
        }
    }
}

/**
 * 用户
 *
 * @author zhaosh
 * @date 2024/02/01
 */
class User {

    /** 用户名 */
    private String userName;
    /** 密码 */
    private String password;

    public User() {

    }

    /**
     * 用户
     *
     * @param userName 用户名
     * @param password 密码
     */
    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    /**
     * 获取用户名
     *
     * @return {@link String}
     */
    public String getUserName() {
        return userName;
    }

    /**
     * 设置用户名
     *
     * @param userName 用户名
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * 获取密码
     *
     * @return {@link String}
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置密码
     *
     * @param password 密码
     */
    public void setPassword(String password) {
        this.password = password;
    }
}