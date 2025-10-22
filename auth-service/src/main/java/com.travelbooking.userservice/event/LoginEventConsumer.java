package com.travelbooking.userservice.event;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;

import java.util.Map;
import java.util.function.Consumer;

@Configuration
public class LoginEventConsumer {

    @Bean
    public Consumer<Message<Map<String, Object>>> loginEventHandler() {
        return message -> {
            Map<String, Object> event = message.getPayload();
            System.out.println("Login event received: " + event);
            // Simulate stream processing: count logins, detect spikes, etc.
        };
    }
}