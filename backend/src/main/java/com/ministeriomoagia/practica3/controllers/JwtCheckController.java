package com.ministeriomoagia.practica3.controllers;

import com.ministeriomoagia.practica3.dto.TokenDTO;
import com.ministeriomoagia.practica3.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("public/api/v1/jwt-check")
public class JwtCheckController {

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping
    public ResponseEntity<?> checkToken(@RequestBody TokenDTO tokenDTO) {
        String token = tokenDTO.getToken();
        String email = jwtUtil.extractUsername(token);
        try {
            // Si el token es válido, devolver un 200 OK
            if (email != null && jwtUtil.validateToken(token, email)) {
                return ResponseEntity.ok().build();
            } else {
                System.out.println("Token invalido");
                // Si el token no es válido, devolver un 401 Unauthorized
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            // En caso de que haya algún error, como token mal formado, devolver 401 Unauthorized
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}
