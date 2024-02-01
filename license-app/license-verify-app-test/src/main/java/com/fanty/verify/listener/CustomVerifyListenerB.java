package com.fanty.verify.listener;

import com.fanty.core.ex.CommonException;
import com.fanty.core.listener.ACustomVerifyListener;
import com.fanty.core.model.LicenseExtraParam;
import org.springframework.stereotype.Component;

/**
 * 自定义验证侦听器
 *
 * @author zhaosh
 * @date 2024/02/01
 */
@Component
public class CustomVerifyListenerB extends ACustomVerifyListener {
    /**
     * 验证
     *
     * @param licenseExtra 额外许可
     * @return boolean
     * @throws CommonException <p>自定义通用异常类</p>
     */
    @Override
    public boolean verify(LicenseExtraParam licenseExtra) throws CommonException {
        System.out.println("======= 自定义证书验证监听器B 实现verify方法  =======");
        return true;
    }
}