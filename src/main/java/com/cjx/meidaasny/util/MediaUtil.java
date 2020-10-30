package com.cjx.meidaasny.util;

import com.cjx.meidaasny.constant.MediaConstant;
import ws.schild.jave.AudioAttributes;
import ws.schild.jave.EncodingAttributes;
import ws.schild.jave.VideoAttributes;
import java.util.Arrays;


/**
*@Description 转码工具类
*@Verson v1.0.0
*@Author cjunxian
*@Date 2020/10/28
*/
public class MediaUtil {

    /**
     * 获取转码属性
     * @return EncodingAttributes 转码属性
     */
    public static EncodingAttributes getEncodingAttributes(String code){
        //转码属性
        EncodingAttributes attrs = new EncodingAttributes();
        AudioAttributes audio = new AudioAttributes();
        VideoAttributes video = new VideoAttributes();
        if(!code.equals(MediaConstant.VIDE_FORMAT)){
        // 转码为MP4
        // 音频编码格式
        audio.setBitRate(MediaConstant.AUDIO_BITRATE);
        audio.setChannels(MediaConstant.AUDIO_CHANNEL);
        audio.setCodec(MediaConstant.AUDIO_CODE);
        // 视频编码格式
        video.setCodec(MediaConstant.VIDEO_CODE);
        video.setBitRate(MediaConstant.VIDEO_BITRATE);
        // 数字设置小了，视频会卡顿
        video.setFrameRate(MediaConstant.VIDEO_FRAMERATE);
        }
        else{
            video.setCodec(VideoAttributes.DIRECT_STREAM_COPY);
            audio.setCodec(AudioAttributes.DIRECT_STREAM_COPY);
        }
        attrs.setFormat(MediaConstant.VIDE_FORMAT);
        attrs.setAudioAttributes(audio);
        attrs.setVideoAttributes(video);
        return attrs;
    }

    /**
     * 后缀识别
     * @param fileName 文件名
     * @return
     */
    public static boolean canConvert(String fileName) {
        if (fileName == null || fileName.length() == 0) {
            return false;
        }

        int extIndex = fileName.lastIndexOf(".");
        if (extIndex == -1) {
            return false;
        }

        final String ext = fileName.substring(extIndex);

        return Arrays.stream(new String[] {".flv", ".avi", ".mp4"})
                .anyMatch(p -> p.equalsIgnoreCase(ext));
    }
}
