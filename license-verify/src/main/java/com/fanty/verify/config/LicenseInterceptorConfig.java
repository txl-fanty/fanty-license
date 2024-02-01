package com.fanty.verify.config;

import com.fanty.verify.interceptor.LicenseVerifyInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 许可证拦截器配置
 *
 * @author zhaosh
 * @date 2024/02/01
 */
@Configuration
public class LicenseInterceptorConfig implements WebMvcConfigurer {

    /**
     * 获取许可证检查拦截器
     *
     * @return {@link LicenseVerifyInterceptor}
     */
    @Bean
    public LicenseVerifyInterceptor getLicenseCheckInterceptor() {
        return new LicenseVerifyInterceptor();
    }

    /**
     * 添加拦截器
     *
     * @param registry 注册表
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(this.getLicenseCheckInterceptor()).addPathPatterns("/**");
    }
}