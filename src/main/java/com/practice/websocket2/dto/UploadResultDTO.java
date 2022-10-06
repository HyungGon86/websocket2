package com.practice.websocket2.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UploadResultDTO {

    private String fileName; // 파일 원본 이름
    private String uuid; // UUID 를 활용한 시스템에 저장되는 이름
    private String fileDir;
    private String roomId;
    private String fileUrl;

    public UploadResultDTO() {
    }

    @Builder
    public UploadResultDTO(String fileName, String uuid, String fileDir, String roomId, String fileUrl) {
        this.fileName = fileName;
        this.uuid = uuid;
        this.fileDir = fileDir;
        this.roomId = roomId;
        this.fileUrl = fileUrl;
    }
}
