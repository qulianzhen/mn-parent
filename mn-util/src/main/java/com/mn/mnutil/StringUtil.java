package com.mn.mnutil;

import org.apache.commons.lang3.StringUtils;

import java.security.InvalidParameterException;

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

    /**
     * 得到驼峰写法
     */
    public static String getTF(String oldString){
        if(oldString != null && !"".equals(oldString)){
            oldString = oldString.toLowerCase();
            for(int i=0,len=oldString.length();i<len;i++){
                if(oldString.charAt(i) == '_'){
                    try{
                        oldString = oldString.substring(0, i+1) + (oldString.charAt(i+1)+"").toUpperCase()+oldString.substring(i+2, oldString.length());
                    }catch(Exception e){
                        e.printStackTrace();
                    }
                }
            }
            oldString = oldString.replace("_", "");
            return oldString;
        }else{
            throw new InvalidParameterException("转换成驼峰写法时出错:无效参数!");
        }
    }
    /**
     * 得到简单名称，如java.util.Date ----> Date
     * @param oldName
     * @return
     */
    public static String getSimpleName(String oldName){
        oldName = oldName.substring(oldName.lastIndexOf(".")+1, oldName.length());
        return oldName;
    }
}
