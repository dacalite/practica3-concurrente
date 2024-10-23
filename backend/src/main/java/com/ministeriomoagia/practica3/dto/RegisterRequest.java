package com.ministeriomoagia.practica3.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RegisterRequest {
    private String name;
    private String email;
    private String password;
    private String passwordConfirm;
}