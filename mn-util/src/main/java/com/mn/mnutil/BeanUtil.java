package com.mn.mnutil;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.util.Collection;

public class BeanUtil {

    /**
     * 复制属性
     * @param dest 目标
     * @param orig 源
     */
    public static void copyProperties(Object dest, Object orig){
        try {
            BeanUtils.copyProperties(dest,orig);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }


    public static Object copyProperties2(Object dest, Object orig) {
        if (dest == null || orig == null) {
            return dest;
        }

        PropertyDescriptor[] destDesc = PropertyUtils.getPropertyDescriptors(dest);
        try {
            for (int i = 0; i < destDesc.length; i++) {
                Class destType = destDesc[i].getPropertyType();
                Class origType = PropertyUtils.getPropertyType(orig, destDesc[i].getName());
                if(destType != null && destType.equals(origType)
                        && !destType.equals(Class.class)) {
                    if(!Collection.class.isAssignableFrom(origType)){
                        try{
                            Object value = PropertyUtils.getProperty(orig, destDesc[i].getName());
                            PropertyUtils.setProperty(dest, destDesc[i].getName(), value);
                        }catch(Exception ex){
                        }
                    }
                }
            }

            return dest;
        }catch(Exception ex) {
            throw new RuntimeException(ex);
//            return dest;
        }
    }

    public static Object copyProperties3(Object dest, Object orig, String[] ignores) {
        if (dest == null || orig == null) {
            return dest;
        }

        PropertyDescriptor[] destDesc = PropertyUtils.getPropertyDescriptors(dest);
        try {
            for (int i = 0; i < destDesc.length; i++) {
                if (contains(ignores, destDesc[i].getName())) {
                    continue;
                }

                Class destType = destDesc[i].getPropertyType();
                Class origType = PropertyUtils.getPropertyType(orig, destDesc[i].getName());
                if(destType != null && destType.equals(origType)
                        && !destType.equals(Class.class)) {
                    if(!Collection.class.isAssignableFrom(origType)){
                        Object value = PropertyUtils.getProperty(orig, destDesc[i].getName());
                        PropertyUtils.setProperty(dest, destDesc[i].getName(), value);
                    }
                }
            }

            return dest;
        }catch(Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    static boolean contains(String[] ignores, String name) {
        boolean ignored = false;
        for (int j = 0; ignores != null && j < ignores.length; j++) {
            if (ignores[j].equals(name)) {
                ignored = true;
                break;
            }
        }

        return ignored;
    }


}
