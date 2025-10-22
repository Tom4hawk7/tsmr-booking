package com.travelbooking.userservice.dto;

public class AuthResponse {
    private String greeting;
    private String token;

    public AuthResponse() {
    }

    public AuthResponse(String greeting, String token) {
        this.greeting = greeting;
        this.token = token;
    }

    public String getGreeting() {
        return greeting;
    }

    public void setGreeting(String greeting) {
        this.greeting = greeting;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}