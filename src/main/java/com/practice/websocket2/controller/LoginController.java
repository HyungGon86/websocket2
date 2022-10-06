package com.practice.websocket2.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Slf4j
@RequiredArgsConstructor
public class LoginController {

    @GetMapping("/chatlogin")
    public String goLogin(){
        return "/chatlogin";
    }
}
