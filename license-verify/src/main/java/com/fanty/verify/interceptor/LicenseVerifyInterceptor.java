package com.fanty.verify.interceptor;

import com.fanty.core.ex.CommonException;
import com.fanty.core.listener.ACustomVerifyListener;
import com.fanty.core.model.LicenseExtraParam;
import com.fanty.core.model.LicenseResult;
import com.fanty.core.model.LicenseVerifyManager;
import com.fanty.core.result.ResultCode;
import com.fanty.core.utils.CommonUtils;
import com.fanty.verify.annotion.VLicense;
import com.fanty.verify.config.LicenseVerifyProperties;
import de.schlichtherle.license.LicenseContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.List;

/**
 * 许可证验证拦截器
 *
 * @author fanty
 * @version v1.0.0
 * @blob https://blog.csdn.net/fanty
 * @date created on 00:32 上午 2020/8/22
 */
public class LicenseVerifyInterceptor implements HandlerInterceptor {

    /** 验证属性 */
    @Autowired
    private LicenseVerifyProperties properties;

    /**
     * 许可证验证拦截器
     */
    public LicenseVerifyInterceptor() {
    }

    /**
     * 预处理
     *
     * @param request  请求
     * @param response 响应
     * @param handler  处理器
     * @return boolean
     * @throws Exception 例外
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            Method method = handlerMethod.getMethod();
            VLicense annotation = method.getAnnotation(VLicense.class);
            if (CommonUtils.isNotEmpty(annotation)) {
                LicenseVerifyManager licenseVerifyManager = new LicenseVerifyManager();
                /** 1、校验证书是否有效 */
                LicenseResult verifyResult = licenseVerifyManager.verify(properties.getVerifyParam());
                if (!verifyResult.getResult()) {
                    throw new CommonException(verifyResult.getMessage());
                }
                LicenseContent content = verifyResult.getContent();
                LicenseExtraParam licenseCheck = (LicenseExtraParam) content.getExtra();
                if (verifyResult.getResult()) {
                    /** 增加业务系统监听，是否自定义验证 */
                    List<ACustomVerifyListener> customListenerList = ACustomVerifyListener.getCustomListenerList();
                    boolean compare = true;
                    for (ACustomVerifyListener listener : customListenerList) {
                        boolean verify = listener.verify(licenseCheck);
                        compare = compare && verify;
                    }
                    return compare;
                }
                throw new CommonException(ResultCode.INTERNAL, verifyResult.getException().getMessage());
            }
        }
        return true;
    }
}