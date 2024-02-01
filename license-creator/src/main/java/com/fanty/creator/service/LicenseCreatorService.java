package com.fanty.creator.service;

import com.fanty.core.model.LicenseCreatorManager;
import com.fanty.core.model.LicenseCreatorParam;
import com.fanty.core.model.LicenseResult;
import com.fanty.core.result.ResponseResult;
import com.fanty.core.utils.DateUtils;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;

/**
 * 许可证创建者服务
 *
 * @author zhaosh
 * @date 2024/02/01
 */
@Service
public class LicenseCreatorService {
    /**
     * 生成许可证
     *
     * @param param 证书创建需要的参数对象
     * @return Map<String, Object>
     */
    public ResponseResult generateLicense(LicenseCreatorParam param) {
        LicenseCreatorManager licenseCreator = new LicenseCreatorManager(param);
        LicenseResult licenseResult = licenseCreator.generateLicense();
        if (licenseResult.getResult()) {
            String message = MessageFormat.format("证书生成成功，证书有效期：{0} - {1}",
                    DateUtils.date2Str(param.getIssuedTime()), DateUtils.date2Str(param.getExpiryTime()));
            return ResponseResult.ok(message, param);
        } else {
            return ResponseResult.fail("证书文件生成失败！");
        }
    }
}