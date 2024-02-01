package com.fanty.verify.config;

import com.fanty.core.helper.LoggerHelper;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * <p>License验证模块自动扫包/装配Bean实例</p>
 *
 * @author zhaosh
 * @date 2024/02/01
 */
@Configuration
@ComponentScan(basePackages = {"com.fanty.verify"})
public class LicenseVerifyAutoConfigure {

    /**
     * 许可证验证自动配置
     */
    public LicenseVerifyAutoConfigure() {
        LoggerHelper.info("============ license-verify-spring-boot-starter initialization！ ===========");
    }
}