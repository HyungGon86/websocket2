package com.practice.websocket2.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ChatUser {

    private String userName;
    private String email;
    private String provider;

    public ChatUser() {
    }

    public ChatUser(String userName, String email, String provider) {
        this.userName = userName;
        this.email = email;
        this.provider = provider;
    }
}
