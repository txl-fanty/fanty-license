package com.fanty.verify.config;

import com.fanty.core.helper.LoggerHelper;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * <p>License验证模块自动扫包/装配Bean实例</p>
 *
 * @author fanty
 * @version v1.0.0
 * @blob https://blog.csdn.net/fanty
 * @date created on 00:02 上午 2020/8/22
 */
@Configuration
@ComponentScan(basePackages = {"com.fanty.verify"})
public class LicenseVerifyAutoConfigure {

    public LicenseVerifyAutoConfigure(){
        LoggerHelper.info("============ license-verify-spring-boot-starter initialization！ ===========");
    }
}
