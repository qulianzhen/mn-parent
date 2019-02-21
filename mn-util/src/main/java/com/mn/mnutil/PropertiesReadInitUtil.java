package com.mn.mnutil;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @ClassName:     PropertiesUtil.java   这个文件只是读指定文件
 * @Description:   操作Properties文件工具类
 * 
 * @author         33107
 * @version        V1.0  
 * @Date           2016年6月13日 下午4:13:58 
 */
public class PropertiesReadInitUtil {
	private static Properties pros = null;
	static{
		 pros = new Properties();
		 InputStream in = PropertiesReadInitUtil.class.getResourceAsStream("/fieldTypeCfg.properties");
		 try{
	         pros.load(in);//Load the file Properties to flow to the object  
	     }catch(IOException e) {
	         e.printStackTrace();
	     }
	}
	
	public static String get(String key){
        return pros.getProperty(key);//Through the key specific get attribute value
    }
	
}
