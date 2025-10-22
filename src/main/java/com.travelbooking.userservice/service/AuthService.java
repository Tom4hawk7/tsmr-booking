package com.travelbooking.userservice.service;

import com.travelbooking.userservice.dto.AuthRequest;
import com.travelbooking.userservice.dto.AuthResponse;
import com.travelbooking.userservice.dto.SignUpRequest;
import com.travelbooking.userservice.entity.User;
import com.travelbooking.userservice.repository.UserRepository;
import com.travelbooking.userservice.service.JwtUtil; // ✅ Adjust if JwtUtil lives elsewhere
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Map;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final StreamBridge streamBridge;

    public AuthService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder,
                       JwtUtil jwtUtil,
                       StreamBridge streamBridge) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
        this.streamBridge = streamBridge;
    }

    public AuthResponse signUp(SignUpRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already registered");
        }

        User user = new User();
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setFullName(request.getFullName());
        user.setRole("USER");

        userRepository.save(user);

        Map<String, Object> payload = Map.of(
                "userId", user.getId(),
                "email", user.getEmail(),
                "fullName", user.getFullName()
        );
        streamBridge.send("userRegistered-out-0", payload);

        String token = jwtUtil.generateToken(user.getEmail());
        return new AuthResponse("User registered successfully", token);
    }

    public AuthResponse signIn(AuthRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Invalid credentials"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        Map<String, Object> loginEvent = Map.of(
                "email", user.getEmail(),
                "timestamp", Instant.now().toString()
        );
        streamBridge.send("userLoggedIn-out-0", loginEvent);

        String token = jwtUtil.generateToken(user.getEmail());
        return new AuthResponse("Welcome back, " + user.getFullName(), token);
    }
}