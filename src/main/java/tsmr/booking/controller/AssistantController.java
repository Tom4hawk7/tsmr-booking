package tsmr.booking.controller;

import org.springframework.web.bind.annotation.*;
import tsmr.booking.ai.BookingAssistant;

@RestController
@RequestMapping("/api/assistant")
public class AssistantController {

    private final BookingAssistant assistant;

    public AssistantController(BookingAssistant assistant) {
        this.assistant = assistant;
    }

    @PostMapping("/chat")
    public String chat(@RequestBody String userMessage) {
        return assistant.chat(userMessage);
    }
}
