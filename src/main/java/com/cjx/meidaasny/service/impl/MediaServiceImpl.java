package com.cjx.meidaasny.service.impl;

import com.cjx.meidaasny.service.MediaService;
import com.cjx.meidaasny.util.FileUtil;
import com.cjx.meidaasny.util.MediaUtil;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import ws.schild.jave.Encoder;
import ws.schild.jave.EncoderException;
import ws.schild.jave.EncodingAttributes;
import ws.schild.jave.MultimediaObject;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;


@Service
public class MediaServiceImpl implements MediaService {

    @Async
    @Override
    public void uploadMedia(String fileName, InputStream inputStream, Long userId) {

        //判断格式是否正确
        if(MediaUtil.canConvert(fileName)){
            //inputStream转本地文件
            File source = FileUtil.createTempFile(inputStream,fileName);
            if(source == null){
                WebSocketService.sendInfo("文件"+fileName+"上传失败",userId);
            }
            //获取转码参数
            EncodingAttributes attrs = MediaUtil.getEncodingAttributes();
            //创建目标文件 路径是你要保存文件的路径，实际上应该是在配置文件中写好。这里为了方便直接写死。
            // 防止重名
            Date date = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
            String time = sdf.format(date);
            File target = new File("E:\\video\\"+time+".mp4");
            //转码部分
            MultimediaObject multimediaObject = new MultimediaObject(source);
            Encoder encoder = new Encoder();
            try {
                //转码并保存
                encoder.encode(multimediaObject,target,attrs);
                //结果可以可以用JSONUtil.toJsonStr(object)返回一个Json字符串
                WebSocketService.sendInfo("视频解析成功,url:"+target.getAbsolutePath(), userId);
            } catch (EncoderException e) {
                e.printStackTrace();
                WebSocketService.sendInfo("转码过程出现错误" + e.getMessage(), userId);
            }finally {
                //删除临时文件
                if(source != null) {
                    source.deleteOnExit();
                }
            }
        }else {
            WebSocketService.sendInfo("文件"+fileName+"文件格式不正确",userId);
        }
    }
}
