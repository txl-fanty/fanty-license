package com.fanty.core.service;

import com.fanty.core.helper.LoggerHelper;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStreamReader;

/**
 * <p>用于获取客户Windows服务器的基本信息</p>
 *
 * @author zhaosh
 * @date 2024/02/01
 */
public class WindowsServerInfos extends AServerInfos {

    /** cpu 命令 */
    private final String CPU_COMMAND = "wmic cpu get processorid";
    /** 主板命令 */
    private final String MAIN_BOARD_COMMAND = "wmic baseboard get serialnumber";

    /**
     * 获取 CPUSellial
     *
     * @return {@link String}
     * @throws Exception 例外
     */
    @Override
    protected String getCPUSerial() throws Exception {
        String result = "";
        try {
            File file = File.createTempFile("tmp", ".vbs");
            file.deleteOnExit();
            FileWriter fw = new FileWriter(file);
            String vbs = "Set objWMIService = GetObject(\"winmgmts:\\\\.\\root\\cimv2\")\n"
                    + "Set colItems = objWMIService.ExecQuery _ \n" + "   (\"Select * from Win32_Processor\") \n"
                    + "For Each objItem in colItems \n" + "    Wscript.Echo objItem.ProcessorId \n"
                    + "    exit for  ' do the first cpu only! \n" + "Next \n";

            fw.write(vbs);
            fw.close();
            Process p = Runtime.getRuntime().exec("cscript //NoLogo " + file.getPath());
            BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line;
            while ((line = input.readLine()) != null) {
                result += line;
            }
            input.close();
            file.delete();
        } catch (Exception e) {
            LoggerHelper.error("获取cpu信息错误", e);
        }
        return result.trim();
//      return GxServerSerialHelper.getWindowsSerial(CPU_COMMAND);
    }

    /**
     * 获取主板串口
     *
     * @return {@link String}
     * @throws Exception 例外
     */
    @Override
    protected String getMainBoardSerial() throws Exception {

        String result = "";
        try {
            File file = File.createTempFile("realhowto", ".vbs");
            file.deleteOnExit();
            FileWriter fw = new FileWriter(file);

            String vbs = "Set objWMIService = GetObject(\"winmgmts:\\\\.\\root\\cimv2\")\n"
                    + "Set colItems = objWMIService.ExecQuery _ \n" + "   (\"Select * from Win32_BaseBoard\") \n"
                    + "For Each objItem in colItems \n" + "    Wscript.Echo objItem.SerialNumber \n"
                    + "    exit for  ' do the first cpu only! \n" + "Next \n";

            fw.write(vbs);
            fw.close();
            Process p = Runtime.getRuntime().exec("cscript //NoLogo " + file.getPath());
            BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line;
            while ((line = input.readLine()) != null) {
                result += line;
            }
            input.close();
        } catch (Exception e) {
            LoggerHelper.error("获取主板信息错误", e);
        }
        return result.trim();
//      return GxServerSerialHelper.getWindowsSerial(MAIN_BOARD_COMMAND);
    }
}