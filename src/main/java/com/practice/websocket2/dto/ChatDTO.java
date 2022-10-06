package com.practice.websocket2.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ChatDTO {

    private MessageType type; // 메시지 타입
    private String roomId; // 방 번호
    private String sender; // 채팅을 보낸 사람
    private String message; // 메시지
    private String time; // 채팅 발송 시간

    private String fileUrl; // 파일 업로드 url
    private String fileName; // 파일이름
    private String fileDir; //  파일 경로

    public ChatDTO() {
    }

    public ChatDTO(MessageType type, String roomId, String sender, String message, String time, String fileUrl, String fileName, String fileDir) {
        this.type = type;
        this.roomId = roomId;
        this.sender = sender;
        this.message = message;
        this.time = time;
        this.fileUrl = fileUrl;
        this.fileName = fileName;
        this.fileDir = fileDir;
    }


}
