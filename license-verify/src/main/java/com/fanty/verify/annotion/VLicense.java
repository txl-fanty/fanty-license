package com.fanty.verify.annotion;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 电子许可证
 *
 * @author zhaosh
 * @date 2024/02/01
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface VLicense {
    /**
     * 验证
     *
     * @return {@link String[]}
     */
    String[] verifies() default {};
}