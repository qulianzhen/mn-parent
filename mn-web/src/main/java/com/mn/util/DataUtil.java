package com.mn.util;

import java.io.*;
import java.util.List;

/**
 * @Description: 数据处理
 * @Author:Mloong
 * @Date :create in 2020/10/28-1:34
 * @Version 1.0
 * @Modified By:
 */
public class DataUtil {

    /**
     * 深度拷贝
     * @param src
     * @param <>
     * @return
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public static <T> List<T> deepCopy(List<T> src) {
        List<T> dest = null;
        try{
            ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
            ObjectOutputStream out = new ObjectOutputStream(byteOut);
            out.writeObject(src);

            ByteArrayInputStream byteIn = new ByteArrayInputStream(byteOut.toByteArray());
            ObjectInputStream in = new ObjectInputStream(byteIn);
            dest = (List<T>) in.readObject();

        }catch(Exception e){
            e.printStackTrace();
        }
        return dest;
    }
}
