package com.mn.mnutil;

import org.apache.commons.io.FileUtils;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;
import java.util.ArrayList;
import java.util.List;
/**
 * 用于IO操作的工具类
 */
public class FileUtil {


    /**
     * 创建文件，如果有父级目录，则先创建目录
     *
     * @param filePath 文件路径
     */
    public static File createFile(String filePath) {
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
            return f;
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

    /**
     * 删除文件
     * @param filePathAndName
     *            String 文件路径及名称 如c:/fqf.txt
     */
    public static void delFile(String filePathAndName) {
        try {
            String filePath = filePathAndName;
            filePath = filePath.toString();
            java.io.File myDelFile = new java.io.File(filePath);
            myDelFile.delete();
        } catch (Exception e) {
            throw new RuntimeException("删除文件操作出错",e);
        }
    }

    /**
     * 读取到字节数组0
     * @param filePath //路径
     * @throws IOException
     */
    public static byte[] getContent(String filePath) throws IOException {
        File file = new File(filePath);
        long fileSize = file.length();
        if (fileSize > Integer.MAX_VALUE) {
            System.out.println("file too big...");
            return null;
        }
        FileInputStream fi = new FileInputStream(file);
        byte[] buffer = new byte[(int) fileSize];
        int offset = 0;
        int numRead = 0;
        while (offset < buffer.length
                && (numRead = fi.read(buffer, offset, buffer.length - offset)) >= 0) {
            offset += numRead;
        }
        // 确保所有数据均被读取
        if (offset != buffer.length) {
            throw new IOException("Could not completely read file " + file.getName());
        }
        fi.close();
        return buffer;
    }

    /**
     * 读取到字节数组1
     *
     * @param filePath
     * @return
     * @throws IOException
     */
    public static byte[] toByteArray(String filePath) throws IOException {

        File f = new File(filePath);
        if (!f.exists()) {
            throw new FileNotFoundException(filePath);
        }
        ByteArrayOutputStream bos = new ByteArrayOutputStream((int) f.length());
        BufferedInputStream in = null;
        try {
            in = new BufferedInputStream(new FileInputStream(f));
            int buf_size = 1024;
            byte[] buffer = new byte[buf_size];
            int len = 0;
            while (-1 != (len = in.read(buffer, 0, buf_size))) {
                bos.write(buffer, 0, len);
            }
            return bos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            bos.close();
        }
    }

    /**
     * 读取到字节数组2
     *
     * @param filePath
     * @return
     * @throws IOException
     */
    public static byte[] toByteArray2(String filePath) throws IOException {
        File f = new File(filePath);
        if (!f.exists()) {
            throw new FileNotFoundException(filePath);
        }
        FileChannel channel = null;
        FileInputStream fs = null;
        try {
            fs = new FileInputStream(f);
            channel = fs.getChannel();
            ByteBuffer byteBuffer = ByteBuffer.allocate((int) channel.size());
            while ((channel.read(byteBuffer)) > 0) {
                // do nothing
                // System.out.println("reading");
            }
            return byteBuffer.array();
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        } finally {
            try {
                channel.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                fs.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Mapped File way MappedByteBuffer 可以在处理大文件时，提升性能
     *
     * @param filePath 文件路径
     * @return
     * @throws IOException
     */
    public static byte[] toByteArray3(String filePath) throws IOException {

        FileChannel fc = null;
        RandomAccessFile rf = null;
        try {
            rf = new RandomAccessFile(filePath, "r");
            fc = rf.getChannel();
            MappedByteBuffer byteBuffer = fc.map(MapMode.READ_ONLY, 0,
                    fc.size()).load();
            //System.out.println(byteBuffer.isLoaded());
            byte[] result = new byte[(int) fc.size()];
            if (byteBuffer.remaining() > 0) {
                // System.out.println("remain");
                byteBuffer.get(result, 0, byteBuffer.remaining());
            }
            return result;
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        } finally {
            try {
                rf.close();
                fc.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 获取某个目录下的所有文件路径--符合freemarker
     * @param resultList
     * @param rootPath
     * @param initFilePath
     * @return
     */
    public static List<String> getAllPath(List<String> resultList, String rootPath, String initFilePath){
        if(resultList == null){
            resultList = new ArrayList<>();
        }
        if(rootPath == null){
            rootPath = new File(initFilePath).getAbsolutePath();
        }
        File file = new File(initFilePath);
        File[] allFiles = file.listFiles();
        if(allFiles!=null && allFiles.length>0){
            for(File currentFile : allFiles){
                if(currentFile.isDirectory()){
                    resultList = getAllPath(resultList,rootPath,currentFile.getAbsolutePath());
                }else{
                    String absPath = currentFile.getAbsolutePath();
                    String relativeFtlPath = "/" + absPath.replace(rootPath+"\\", "").replace("\\", "/");
                    resultList.add(currentFile.getName() +";"+ relativeFtlPath);
                }
            }
        }
        return resultList;
    }

    public static void main(String[] args) {
        //FileUtils.copyDirectory("","");
        //copyFile(new File("G:\\cxfCode2\\msun\\submission\\service\\FindEcgFileXml.java"), new File("G:\\cxfCode7\\code7.java"));

    }

}
