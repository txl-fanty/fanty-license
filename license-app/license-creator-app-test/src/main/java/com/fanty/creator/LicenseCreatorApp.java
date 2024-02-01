package com.fanty.creator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * 许可证创建者应用程序
 *
 * @author zhaosh
 * @date 2024/02/01
 */
@SpringBootApplication
public class LicenseCreatorApp extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(LicenseCreatorApp.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(LicenseCreatorApp.class);
    }
}
