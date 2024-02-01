package com.fanty.creator.config;

import com.fanty.core.helper.LoggerHelper;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * <p>License生成模块自动扫包/装配Bean实例</p>
 *
 * @author zhaosh
 * @date 2024/02/01
 */
@Configuration
@ComponentScan(basePackages = {"com.fanty.creator"})
@EnableConfigurationProperties({LicenseCreatorProperties.class})
public class LicenseCreatorAutoConfigure {
    /**
     * 许可证创建者自动配置
     */
    public LicenseCreatorAutoConfigure() {
        LoggerHelper.info("============ license-creator-spring-boot-starter initialization！ ===========");
    }
}