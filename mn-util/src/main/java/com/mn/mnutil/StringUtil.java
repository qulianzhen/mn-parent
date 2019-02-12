package com.mn.mnutil;

import org.apache.commons.lang3.StringUtils;

/**
 * 用于字符串操作的工具类
 */
public class StringUtil {

    /**
     * 判断是否为null, 空字符串, 空格组成的字符串 ,如果是，则返回true
     *
     * @param cs
     * @return
     */
    public static boolean isBlank(CharSequence cs) {
        return StringUtils.isBlank(cs);
    }

    /**
     * 判断是否为null, 空字符串, 空格组成的字符串 ,如果不是，则返回true
     *
     * @param cs
     * @return
     */
    public static boolean isNotBlank(CharSequence cs) {
        return StringUtils.isNotBlank(cs);
    }

    /**
     * 判断是否为null, 空字符串 ,如果是，则返回true
     *
     * @param cs
     * @return
     */
    public static boolean isEmpty(CharSequence cs) {
        return StringUtils.isEmpty(cs);
    }

    /**
     * 判断是否为null, 空字符串 ,如果不是，则返回true
     *
     * @param cs
     * @return
     */
    public static boolean isNotEmpty(CharSequence cs) {
        return StringUtils.isNotEmpty(cs);
    }

    /**
     * 右补齐
     *
     * @param str    原字符串
     * @param size   期望长度
     * @param padStr 用什么来补充
     * @return
     */
    public static String rightPad(String str, int size, String padStr) {
        return StringUtils.rightPad(str, size, padStr);
    }

    /**
     * 左补齐
     *
     * @param str    原字符串
     * @param size   期望长度
     * @param padStr 用什么来补充
     * @return
     */
    public static String leftPad(String str, int size, String padStr) {
        return StringUtils.leftPad(str, size, padStr);
    }

    /**
     * 去掉最后一个字符
     *
     * @param str 原字符串
     * @return
     */
    public static String chop(String str) {
        return StringUtils.chop(str);
    }
}
