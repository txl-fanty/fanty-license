package com.fanty.creator.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.io.File;

/**
 * 许可证创建者属性
 *
 * @author zhaosh
 * @date 2024/02/01
 */
@ConfigurationProperties(prefix = "springboot.license.generate")
public class LicenseCreatorProperties {

    /** 证书生成临时存放路径 */
    private String tempPath;

    /**
     * 许可证创建者属性
     */
    public LicenseCreatorProperties() {
    }

    /**
     * 获取临时路径
     *
     * @return {@link String}
     */
    public String getTempPath() {
        return tempPath;
    }

    /**
     * 设置临时路径
     *
     * @param tempPath 临时路径
     */
    public void setTempPath(String tempPath) {
        this.tempPath = tempPath;
        File file = new File(tempPath);
        if (!file.exists()) {
            file.mkdirs();
        }
    }
}