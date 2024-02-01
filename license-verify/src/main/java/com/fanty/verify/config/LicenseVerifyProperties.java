package com.fanty.verify.config;

import com.fanty.core.model.LicenseVerifyParam;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 许可证验证属性
 *
 * @author zhaosh
 * @date 2024/02/01
 */
@Component
@ConfigurationProperties(prefix = "springboot.license.verify")
public class LicenseVerifyProperties {

    /** 主题 */
    private String subject;
    /** 公共别名 */
    private String publicAlias;
    /** 公钥存储路径 */
    private String publicKeysStorePath = "";
    /** 商店通行证 */
    private String storePass = "";
    /** 许可证路径 */
    private String licensePath;

    /**
     * 许可证验证属性
     */
    public LicenseVerifyProperties() {
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getPublicAlias() {
        return publicAlias;
    }

    public void setPublicAlias(String publicAlias) {
        this.publicAlias = publicAlias;
    }

    public String getPublicKeysStorePath() {
        return publicKeysStorePath;
    }

    public void setPublicKeysStorePath(String publicKeysStorePath) {
        this.publicKeysStorePath = publicKeysStorePath;
    }

    public String getStorePass() {
        return storePass;
    }

    public void setStorePass(String storePass) {
        this.storePass = storePass;
    }

    public String getLicensePath() {
        return licensePath;
    }

    public void setLicensePath(String licensePath) {
        this.licensePath = licensePath;
    }

    public LicenseVerifyParam getVerifyParam() {
        LicenseVerifyParam param = new LicenseVerifyParam();
        param.setSubject(subject);
        param.setPublicAlias(publicAlias);
        param.setStorePass(storePass);
        param.setLicensePath(licensePath);
        param.setPublicKeysStorePath(publicKeysStorePath);
        return param;
    }
}