package com.cjx.meidaasny.controller;

import com.cjx.meidaasny.service.MediaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

@RestController
@RequestMapping("media")
public class MediaController {

    @Autowired
    MediaService mediaService;

    @PostMapping
    public String uploadMedia(MultipartFile file,Long userId) throws IOException {
        String fileName = file.getOriginalFilename();
        InputStream in = file.getInputStream();
        mediaService.uploadMedia(fileName,in,userId);
        return "用户"+userId+"正在对"+fileName+"文件进行在线解析";
    }

}
