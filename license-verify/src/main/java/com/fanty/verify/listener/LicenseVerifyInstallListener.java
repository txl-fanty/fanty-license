package com.fanty.verify.listener;

import com.fanty.core.helper.LoggerHelper;
import com.fanty.core.model.LicenseResult;
import com.fanty.core.model.LicenseVerifyManager;
import com.fanty.core.utils.CommonUtils;
import com.fanty.verify.config.LicenseVerifyProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * <p>项目启动时安装证书&定时检测lic变化，自动更替lic</p>
 *
 * @author fanty
 * @version v1.0.0
 * @blob https://blog.csdn.net/fanty
 * @date created on 00:02 上午 2020/8/22
 */
@Component
public class LicenseVerifyInstallListener implements ApplicationListener<ContextRefreshedEvent> {

    /** 性能 */
    @Autowired
    private LicenseVerifyProperties properties;

    /** 文件唯一身份标识 == 相当于人类的指纹一样 */
    private static String md5 = "";

    /** 是否负载 */
    private static boolean isLoad = false;

    /**
     * 应用程序事件
     *
     * @param event 事件
     */
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (CommonUtils.isNotEmpty(properties.getLicensePath())) {
            try {
                install();

                String readMd5 = getMd5(properties.getLicensePath());
                isLoad = true;
                if (LicenseVerifyInstallListener.md5 == null || "".equals(LicenseVerifyInstallListener.md5)) {
                    LicenseVerifyInstallListener.md5 = readMd5;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 定时器 5秒检测一次，不能太快也不能太慢 @throws Exception 例外
     */
    @Scheduled(cron = "0/5 * * * * ?")
    protected void timer() throws Exception {
        if (!isLoad) {
            return;
        }
        String readMd5 = getMd5(properties.getLicensePath());
        // 不相等，说明lic变化了
        if (!readMd5.equals(LicenseVerifyInstallListener.md5)) {
            install();
            LicenseVerifyInstallListener.md5 = readMd5;
        }
    }

    /**
     * 安装
     */
    private void install() {
        LoggerHelper.info("++++++++ 开始安装证书 ++++++++");
        LicenseVerifyManager licenseVerifyManager = new LicenseVerifyManager();
        /** 走定义校验证书并安装 */
        LicenseResult result = licenseVerifyManager.install(properties.getVerifyParam());
        if (result.getResult()) {
            LoggerHelper.info("++++++++ 证书安装成功 ++++++++");
        } else {
            LoggerHelper.info("++++++++ 证书安装失败 ++++++++");
        }
    }

    /**
     * <p>获取文件的md5</p>
     *
     * @param filePath 文件路径
     * @return {@link String}
     * @throws Exception 例外
     */
    public String getMd5(String filePath) throws Exception {
        File file;
        String md5 = "";
        try {
            file = ResourceUtils.getFile(filePath);
            if (file.exists()) {
                FileInputStream is = new FileInputStream(file);
                byte[] data = new byte[is.available()];
                is.read(data);
                md5 = DigestUtils.md5DigestAsHex(data);
                is.close();
            }
        } catch (FileNotFoundException e) {

        }
        return md5;
    }
}