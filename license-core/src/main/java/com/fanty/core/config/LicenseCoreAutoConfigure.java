package com.fanty.core.config;

import com.fanty.core.helper.LoggerHelper;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * <p>license-core模块中的Bean实现自动装配 -- 配置类</p>
 *
 * @author zhaosh
 * @date 2024/02/01
 */
@Configuration
@ComponentScan(basePackages = {"com.fanty.core"})
public class LicenseCoreAutoConfigure {
    /**
     * 许可证核心自动配置
     */
    public LicenseCoreAutoConfigure() {
        LoggerHelper.info("============ license-core-spring-boot-starter initialization！ ===========");
    }
}