package com.practice.websocket2.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Getter
@Setter
@Builder
public class ChatRoom {

    private String roomId; // 채팅방 아이디
    private String roomName; // 채팅방 이름
    private long userCount; // 채팅방 인원수
    private int maxUserCnt; // 채팅방 최대 인원 제한

    private String roomPwd; // 채팅방 삭제시 필요한 pwd
    private boolean secretChk; // 채팅방 잠금 여부

    private Map<String, String> userlist = new HashMap<String, String>();

    public ChatRoom create(String roomName){
        ChatRoom chatRoom = new ChatRoom();
        chatRoom.roomId = UUID.randomUUID().toString();
        chatRoom.roomName = roomName;

        return chatRoom;
    }

    public ChatRoom() {
    }

    public ChatRoom(String roomId, String roomName, long userCount, int maxUserCnt, String roomPwd, boolean secretChk, Map<String, String> userlist) {
        this.roomId = roomId;
        this.roomName = roomName;
        this.userCount = userCount;
        this.maxUserCnt = maxUserCnt;
        this.roomPwd = roomPwd;
        this.secretChk = secretChk;
        this.userlist = userlist;
    }
}
