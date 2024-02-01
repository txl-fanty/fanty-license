package com.fanty.verify.listener;

import com.fanty.core.ex.CommonException;
import com.fanty.core.listener.ACustomVerifyListener;
import com.fanty.core.model.LicenseExtraParam;
import org.springframework.stereotype.Component;

/**
 * <p>Lic自定义验证监听器B</p>
 *
 * @author fanty
 * @version v1.0.0
 * @blob https://blog.csdn.net/fanty
 * @date created on 10:24 下午 2020/8/21
 */
@Component
public class CustomVerifyListenerB extends ACustomVerifyListener {
    @Override
    public boolean verify(LicenseExtraParam licenseExtra) throws CommonException {
        System.out.println("======= 自定义证书验证监听器B 实现verify方法  =======");
        return true;
    }
}
