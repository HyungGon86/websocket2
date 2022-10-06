package com.practice.websocket2.repository;

import com.practice.websocket2.dto.ChatRoom;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class ChatRepositoryTest {

    private Map<String, ChatRoom> chatRoomMap;

    @AfterEach
    void afterEach() {
        chatRoomMap.clear();
    }

    @BeforeEach
    void beforeEach() {
        chatRoomMap = new LinkedHashMap<>();
    }

   @Test
   void 방_비밀번호체크() throws Exception {
       //given
       String roomPwd = String.valueOf(1986);
       String roomName = "테스트";

       ChatRoom chatRoom = new ChatRoom().create(roomName);
       chatRoom.setRoomPwd(roomPwd);

       chatRoomMap.put(chatRoom.getRoomId(), chatRoom);

       //when
       boolean result = roomPwd.equals(chatRoomMap.get(chatRoom.getRoomId()).getRoomPwd());

       //then
       assertThat(result).isTrue();
   }

   @Test
   void test() throws Exception {
       //given

       //when

       //then
   }
}