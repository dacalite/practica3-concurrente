package com.ministeriomoagia.practica3.controllers;

import com.ministeriomoagia.practica3.dto.LoginRequest;
import com.ministeriomoagia.practica3.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("public/login")
public class LoginController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
        boolean isValid = userService.verifyUser(loginRequest.getEmail(), loginRequest.getPassword());

        if (isValid) {
            return ResponseEntity.ok("Login succeed");
        } else {
            return ResponseEntity.status(401).body("Invalid user or password");
        }
    }
}


