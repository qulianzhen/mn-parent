package com.mn.mnutil;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

/**
 * 用于IO操作的工具类
 */
public class FileUtil {

    /**
     * 创建文件，如果有父级目录，则先创建目录
     *
     * @param filePath 文件路径
     */
    public static void createFile(String filePath) {
        try {
            File f = new File(filePath);
            if (!f.getParentFile().exists()) {
                f.getParentFile().mkdirs();
            }
            if (!f.exists()) {
                f.createNewFile();
            } else {//如果文件已经存在，是否需要先删除原文件，再创建新文件，还是直接不操作！
                /*删除重建写法*/
                //f.delete();
                //f.createNewFile();
            }
        } catch (Exception e) {
            throw new RuntimeException("创建文件失败！", e);
        }
    }

    /**
     * 创建目录，如果有父级目录，则先创建目录
     *
     * @param dirPath 目录路径
     */
    public static void createDirectory(String dirPath) {
        try {
            File f = new File(dirPath);
            if (!f.exists()) {
                f.mkdirs();
            }
        } catch (Exception e) {
            throw new RuntimeException("创建目录失败！", e);
        }
    }

    /**
     * 复制目录[是把源路径“里面的”所有东西 复制到   目标路径下]
     *
     * @param srcFile  源目录
     * @param descFile 目标目录
     */
    public static void copyDirectory(File srcFile, File descFile) {
        try {
            FileUtils.copyDirectory(srcFile, descFile);
        } catch (IOException e) {
            throw new RuntimeException("复制目录出错!", e);
        }
    }

    /**
     * 复制文件
     *
     * @param srcFile  源文件路径 - 是文件路径，包含文件名，如G:\cxfCode2\1.txt
     * @param descFile 目标文件路径 --是文件的路径，包含文件名，而且，路径必须存在，否则报错 如D:\xx\2.txt
     */
    public static void copyFile(File srcFile, File descFile) {
        try {
            FileUtils.copyFile(srcFile, descFile);
        } catch (IOException e) {
            throw new RuntimeException("复制文件出错!", e);
        }
    }

    public static void main(String[] args) {
        //FileUtils.copyDirectory("","");
        copyFile(new File("G:\\cxfCode2\\msun\\submission\\service\\FindEcgFileXml.java"), new File("G:\\cxfCode7\\code7.java"));
    }

}
