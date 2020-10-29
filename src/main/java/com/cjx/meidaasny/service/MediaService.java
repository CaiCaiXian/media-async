package com.cjx.meidaasny.service;

import java.io.InputStream;

public interface MediaService {

    /**
     * 媒体上传解析的方法
     * @param fileName 文件名
     * @param inputStream 文件输入流
     * @param userId 用户id
     */
    void uploadMedia(String fileName, InputStream inputStream, Long userId);
}
