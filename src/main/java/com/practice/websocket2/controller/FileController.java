package com.practice.websocket2.controller;

import com.practice.websocket2.dto.UploadResultDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriUtils;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@RestController
@Slf4j
@RequestMapping("/websocket")
public class FileController {

    @Value("${com.upload.path}")
    private String uploadPath;

    // 프론트에서 ajax 를 통해 /upload 로 MultipartFile 형태로 파일과 roomId 를 전달받는다.
    // 전달받은 file 를 uploadFile 메서드를 통해 업로드한다.
    @PostMapping("/upload")
    public UploadResultDTO uploadFile(@RequestParam MultipartFile file, String roomId) throws MalformedURLException {

        // 실제 파일 이름 IE나 Edge는 전체 경로가 들어오므로
        String originalName = file.getOriginalFilename();

        String fileName = originalName.substring(originalName.lastIndexOf("\\") + 1);

//        // 날짜 폴더 생성
//        String fileDir = makeFolder(roomId);

        //UUID
        String uuid = UUID.randomUUID().toString();

        //저장할 파일 이름
        String path = uploadPath + File.separator + roomId;
        String saveName = uploadPath + File.separator + roomId + File.separator + uuid + fileName; // 저장되는 파일 경로
        File forderPath = new File(path);

        if (!forderPath.exists()) {
            forderPath.mkdirs();
        }

        Path savePath = Paths.get(saveName);
        Path subpath = savePath.subpath(1, 4);
        log.info("subpath : {}", subpath.toString());

        try {
            file.transferTo(savePath);// 실제 이미지 저장

            UploadResultDTO resultDTO = new UploadResultDTO();
            resultDTO.setFileName(fileName);
            resultDTO.setUuid(uuid);
            resultDTO.setFileDir(savePath.toString());
            resultDTO.setRoomId(roomId);
            resultDTO.setFileUrl(File.separator + subpath.toString());

            return resultDTO;

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private String makeFolder(String roomId) {

        String str = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));

        String folderPath = str.replace("/", File.separator);

        // make folder ----
        File uploadPatheFolder = new File(uploadPath, folderPath + File.separator + roomId);

        if (!uploadPatheFolder.exists()) {
            uploadPatheFolder.mkdirs();
        }

        return folderPath;
    }

    @GetMapping("/download/{fileName}")
    public ResponseEntity<Resource> download(@PathVariable String fileName, @RequestParam String fileDir) throws MalformedURLException {

        log.info("파일 이름 : {}", fileName);
        log.info("파일 경로 : {}", fileDir);

        UrlResource resource = new UrlResource("file:" + fileDir);

        //한글 파일 이름이나 특수 문자의 경우 깨질 수 있으니 인코딩 한번 해주기
        String encodedUploadFileName = UriUtils.encode(fileName,
                StandardCharsets.UTF_8);

        //아래 문자를 ResponseHeader에 넣어줘야 한다. 그래야 링크를 눌렀을 때 다운이 된다.
        //정해진 규칙이다.
        String contentDisposition = "attachment; filename=\"" + encodedUploadFileName + "\"";

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, contentDisposition)
                .body(resource);
    }

}
