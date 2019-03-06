package com.mn.commonbean.exception;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

/**
 * @Description: 异常码-异常信息类
 * @Author:Mloong
 * @Date :create in 2019/3/6-22:11
 * @Version 1.0
 * @Modified By:
 */
public class CodeMsgInfo {

    public static final Map<Integer,String> codeMsgMap = new HashMap<>();

    //将业务异常码配置读入内存
    static{
        Properties prop = new Properties();
        InputStream in = CodeMsgInfo.class.getResourceAsStream("/businesscode.properties");
        try {
            prop.load(in);
            Set<Object> keySet = prop.keySet();
            for(Object key:keySet){
                codeMsgMap.put(Integer.parseInt(key.toString()), (String)prop.get(key));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (in != null) in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}
