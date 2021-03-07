package com.mn.mnutil;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Description: 时间工具类
 * @Author:Mloong
 * @Date :create in 2021/3/7-19:37
 * @Version 1.0
 * @Modified By:
 */
public class DateUtil {
    private DateUtil(){}
    public static String getCurrentDate(){
        return getCurrentDate("yyyy-MM-dd HH:mm:ss");
    }

    public static String getCurrentDate(String format){
        Date date = new Date();
        return new SimpleDateFormat(format).format(date);
    }

}
