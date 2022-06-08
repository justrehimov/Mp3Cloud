package com.mp3.cloud.controller;

import com.mp3.cloud.service.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserServiceImpl userService;

    @PostMapping("/register")
    public String register(@RequestParam("name") String name, @RequestParam("surname") String surname,
                           @RequestParam("username") String username,@RequestParam("password") String password){
        return userService.register(name, surname, username, password);
    }

    @GetMapping("/register")
    public String register(){
       return "register";
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }
}
