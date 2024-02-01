package com.fanty.creator.config;

import com.fanty.core.helper.LoggerHelper;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * <p>License生成模块自动扫包/装配Bean实例</p>
 *
 * @author fanty
 * @version v1.0.0
 * @blob https://blog.csdn.net/fanty
 * @date created on 11:33 下午 2020/8/21
 */
@Configuration
@ComponentScan(basePackages = {"com.fanty.creator"})
@EnableConfigurationProperties({LicenseCreatorProperties.class})
public class LicenseCreatorAutoConfigure {
    public LicenseCreatorAutoConfigure(){
        LoggerHelper.info("============ license-creator-spring-boot-starter initialization！ ===========");
    }
}
