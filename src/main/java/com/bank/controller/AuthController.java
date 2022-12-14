package com.bank.controller;

import com.bank.entities.Session;
import com.bank.services.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth/")
public class AuthController {
    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("login")
    public Session login(@RequestBody AuthentificateInfo authentificateInfo) {
        return userService.auth(authentificateInfo);
    }
}
