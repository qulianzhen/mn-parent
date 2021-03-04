package com.mn.util;

import javax.servlet.http.HttpServletRequest;

/**
 * @Description: 获取客户端ip-根据实际情况修改
 * @Author:Mloong
 * @Date :create in 2020/10/7-22:00
 * @Version 1.0
 * @Modified By:
 */
public class IPUtil {
    public static String getRequestRealIp(HttpServletRequest request) {

        //nginx 反向代理链方式
        String ip = request.getHeader("x-forwarded-for");
        if (ip != null && ip.contains(",")) {
            ip = ip.split(",")[0];
        }

        if (!checkIp(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (!checkIp(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        //nginx 指定
        if (!checkIp(ip)) {
            ip = request.getHeader("X-Real-IP");
        }

        if (!checkIp(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    private static boolean checkIp(String ip) {
        if (ip == null || ip.length() == 0 || "unkown".equalsIgnoreCase(ip) ) {
            return false;
        }
        return true;
    }
}
