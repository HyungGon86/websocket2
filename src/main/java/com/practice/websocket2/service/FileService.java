package com.practice.websocket2.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;

@Service
@Slf4j
@RequiredArgsConstructor
public class FileService {

    @Value("${com.upload.path}")
    private String uploadPath;

    public void deleteFileDir(String roomId) throws IOException {

        String path = uploadPath + File.separator + roomId;
        File roomPath = new File(path);

        FileUtils.deleteDirectory(roomPath);
        roomPath.delete();
    }
}
