package com.ministeriomoagia.practica3.aspects;

import com.ministeriomoagia.practica3.utils.JwtUtil;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.JoinPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AuthAspect {

    @Autowired
    private JwtUtil jwtUtil;

    // This method runs before any method annotated with @RequiresAuth
    @Before("@annotation(com.ministeriomoagia.practica3.annotations.RequiresAuth) && args(.., headers)")
    public void checkAuthentication(JoinPoint joinPoint, HttpHeaders headers) {
        String authorizationHeader = headers.getFirst(HttpHeaders.AUTHORIZATION);

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            String token = authorizationHeader.substring(7);
            String email = jwtUtil.extractUsername(token);

            if (email != null && jwtUtil.validateToken(token, email)) {
                System.out.println("Authentication successful for user: " + email);
                return; // Authentication successful
            }
        }

        throw new RuntimeException("Authentication failed: Invalid or missing token.");
    }
}
