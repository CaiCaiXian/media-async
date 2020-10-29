package com.cjx.meidaasny.util;

import org.springframework.util.StringUtils;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 文件工具类
 */
public class FileUtil {

    /**
     * 获取文件后缀，带（.）
     *
     * @param fileName 文件名
     * @return 后缀 extend
     */
    public static String getExtend(String fileName) {
        if (StringUtils.isEmpty(fileName)) {
            return "";
        }
        int iPos = fileName.lastIndexOf('.');
        if (iPos < 0) {
            return "";
        }
        return fileName.substring(iPos).toLowerCase();
    }

    public static File createTempFile(InputStream inputStream, String fileName){

        //防止多线程情况下重名
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss");
        String time = sdf.format(date);
        //文件名上加上日期，防止多线程时文件被覆盖
        try {
            //创建临时文件temp
            //将inputStream写进temp;
            File temp = File.createTempFile("video"+time,getExtend(fileName));
            OutputStream outputStream = new FileOutputStream(temp);
            int index;
            byte[] buffer = new byte[1024];
            while ((index = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, index);
            }
            outputStream.flush();
            inputStream.close();
            outputStream.close();
            return temp;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}
