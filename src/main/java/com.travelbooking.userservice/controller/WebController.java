package com.travelbooking.userservice.controller;

import com.travelbooking.userservice.dto.AuthRequest;
import com.travelbooking.userservice.dto.AuthResponse;
import com.travelbooking.userservice.dto.SignUpRequest;
import com.travelbooking.userservice.service.AuthService;
import com.travelbooking.userservice.service.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class WebController {

    private final AuthService authService;
    private final JwtUtil jwtUtil;

    @Autowired
    public WebController(AuthService authService, JwtUtil jwtUtil) {
        this.authService = authService;
        this.jwtUtil = jwtUtil;
    }

    @GetMapping("/signup")
    public String showSignupForm() {
        return "signup";
    }

    @GetMapping("/signin")
    public String showSigninForm() {
        return "signin";
    }

    @PostMapping("/signup")
    public String handleSignup(@RequestParam String fullName,
                               @RequestParam String email,
                               @RequestParam String password,
                               Model model) {
        SignUpRequest request = new SignUpRequest();
        request.setFullName(fullName);
        request.setEmail(email);
        request.setPassword(password);

        try {
            AuthResponse response = authService.signUp(request);
            model.addAttribute("message", response.getGreeting());
            return "signin";
        } catch (Exception e) {
            model.addAttribute("error", "Signup failed: " + e.getMessage());
            return "signup";
        }
    }

    @PostMapping("/signin")
    public String handleSignin(@RequestParam String email,
                               @RequestParam String password,
                               Model model) {
        AuthRequest request = new AuthRequest();
        request.setEmail(email);
        request.setPassword(password);

        try {
            AuthResponse response = authService.signIn(request);
            model.addAttribute("message", response.getGreeting());
            model.addAttribute("token", response.getToken());
            return "homepage";
        } catch (Exception e) {
            model.addAttribute("error", "Login failed: " + e.getMessage());
            return "signin";
        }
    }

    @GetMapping("/home")
    public String home(@RequestParam String token, Model model) {
        String email = jwtUtil.extractEmail(token);
        model.addAttribute("message", "Welcome, " + email);
        return "homepage";
    }
}