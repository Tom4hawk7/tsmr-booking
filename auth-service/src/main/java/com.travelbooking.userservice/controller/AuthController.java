package com.travelbooking.userservice.controller;

import com.travelbooking.userservice.dto.AuthRequest;
import com.travelbooking.userservice.dto.AuthResponse;
import com.travelbooking.userservice.dto.SignUpRequest;
import com.travelbooking.userservice.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/signup")
    public ResponseEntity<AuthResponse> signUp(@RequestBody SignUpRequest request) {
        return ResponseEntity.ok(authService.signUp(request));
    }

    @PostMapping("/signin")
    public ResponseEntity<AuthResponse> signIn(@RequestBody AuthRequest request) {
        return ResponseEntity.ok(authService.signIn(request));
    }
}