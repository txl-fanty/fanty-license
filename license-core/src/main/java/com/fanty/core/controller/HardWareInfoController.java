package com.fanty.core.controller;

import com.fanty.core.result.ResponseResult;
import com.fanty.core.service.AServerInfos;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>服务器硬件信息获取API</p>
 *
 * @author zhaosh
 * @date 2024/02/01
 */
@CrossOrigin
@RestController
@RequestMapping("/license")
public class HardWareInfoController {

    /**
     * 获取服务器信息
     *
     * @param osName 操作系统类型，如果为空则自动判断
     * @return {@link ResponseResult}
     */
    @RequestMapping(value = "/getServerInfos", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseResult getServerInfos(@RequestParam(value = "osName", required = false) String osName) {
        return ResponseResult.ok(AServerInfos.getServer(osName).getServerInfos());
    }
}