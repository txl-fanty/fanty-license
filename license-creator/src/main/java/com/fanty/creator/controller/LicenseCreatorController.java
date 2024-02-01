package com.fanty.creator.controller;

import com.fanty.core.ex.CommonException;
import com.fanty.core.model.LicenseCreatorParam;
import com.fanty.core.result.ResponseResult;
import com.fanty.core.service.AServerInfos;
import com.fanty.core.utils.CommonUtils;
import com.fanty.creator.config.LicenseCreatorProperties;
import com.fanty.creator.service.LicenseCreatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;

/**
 * <p>用于生成证书文件 == !!!不能放在给客户部署的服务器上，以免客户自己调用生成lic</p>
 *
 * @author zhaosh
 * @date 2024/02/01
 */
@CrossOrigin
@RestController
@RequestMapping("/license")
public class LicenseCreatorController {

    /** LIC 前缀 URL */
    @Value("${springboot.license.server.prefix:http://localhost:8080/license/}")
    private String licPrefixUrl;

    /** 创作者服务 */
    @Autowired
    private LicenseCreatorService creatorService;

    /** 性能 */
    @Autowired
    private LicenseCreatorProperties properties;

    /**
     * <p>生成证书</p>
     *
     * @param param 生成证书需要的参数，如：
     * @return {@link ResponseResult}
     * @throws Exception 例外
     */
    @PostMapping("/generate")
    public ResponseResult generate(@RequestBody LicenseCreatorParam param) throws Exception {
        // 如果没有人为的指定lic要生成的位置，则程序自动处理
        if (CommonUtils.isEmpty(param.getLicensePath())) {
            //设置格式
            SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
            String tempPath = properties.getTempPath();
            if (tempPath == null || "".equals(tempPath)) {
                // 如果默认临时文件等于空的话，就获取当前服务执行的路径
                tempPath = AServerInfos.getServerTempPath();
            }
            // 根据时间戳，命名lic文件
            String licDir = tempPath + "/license/" + format.format(System.currentTimeMillis());
            File file = new File(licDir);
            if (!file.exists()) {
                if (!file.mkdirs()) {
                    throw new CommonException("创建目录" + licDir + ",失败，请检查是是否有创建目录的权限或者手动进行创建！");
                }
            }
            /**统一下路径分隔符*/
            param.setLicensePath(licDir.replace("\\", "/") + "/license.lic");
        }
        param.setLicUrl(licPrefixUrl + "download?path=" + param.getLicensePath());
        return creatorService.generateLicense(param);
    }

    /**
     * 下载
     *
     * @param path     路径
     * @param request  请求
     * @param response 响应
     * @throws Exception 例外
     */
    @GetMapping("/download")
    public void downLoad(@RequestParam(value = "path") String path, HttpServletRequest request, HttpServletResponse response) throws Exception {
        File file = new File(path);
        if (!file.exists()) {
            response.setStatus(HttpStatus.NOT_FOUND.value());
            return;
        }
        InputStream is = new FileInputStream(file);
        String fileName = file.getName();
        // 设置文件ContentType类型，这样设置，会自动判断下载文件类型
        response.setContentType("multipart/form-data");
        // 设置编码格式
        response.setCharacterEncoding("UTF-8");
        // 设置可以识别Html文件
        response.setContentType("text/html");
        // 设置头中附件文件名的编码
        setAttachmentCoding(request, response, fileName);
        // 设置文件头：最后一个参数是设置下载文件名
//        response.setHeader("Content-Disposition", "attachment;fileName="+file.getName()+".lic");
        BufferedInputStream bis = new BufferedInputStream(is);
        OutputStream os = response.getOutputStream();
        byte[] buffer = new byte[1024 * 10];
        int length;
        while ((length = bis.read(buffer, 0, buffer.length)) != -1) {
            os.write(buffer, 0, length);
        }
        os.close();
        bis.close();
        is.close();
    }

    /**
     * 设置附件编码
     *
     * @param request  请求
     * @param response 响应
     * @param fileName 文件名
     */
    private void setAttachmentCoding(HttpServletRequest request, HttpServletResponse response, String fileName) {
        String browser;
        try {
            browser = request.getHeader("User-Agent");
            if (-1 < browser.indexOf("MSIE 6.0") || -1 < browser.indexOf("MSIE 7.0")) {
                // IE6, IE7 浏览器
                response.addHeader("content-disposition", "attachment;filename="
                        + new String(fileName.getBytes(), "ISO8859-1"));
            } else if (-1 < browser.indexOf("MSIE 8.0")) {
                // IE8
                response.addHeader("content-disposition", "attachment;filename="
                        + URLEncoder.encode(fileName, "UTF-8"));
            } else if (-1 < browser.indexOf("MSIE 9.0")) {
                // IE9
                response.addHeader("content-disposition", "attachment;filename="
                        + URLEncoder.encode(fileName, "UTF-8"));
            } else if (-1 < browser.indexOf("Chrome")) {
                // 谷歌
                response.addHeader("content-disposition",
                        "attachment;filename*=UTF-8''" + URLEncoder.encode(fileName, "UTF-8"));
            } else if (-1 < browser.indexOf("Safari")) {
                // 苹果
                response.addHeader("content-disposition", "attachment;filename="
                        + new String(fileName.getBytes(), "ISO8859-1"));
            } else {
                // 火狐或者其他的浏览器
                response.addHeader("content-disposition",
                        "attachment;filename*=UTF-8''" + URLEncoder.encode(fileName, "UTF-8"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}