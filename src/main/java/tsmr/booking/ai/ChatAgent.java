package tsmr.booking.ai;

import dev.langchain4j.service.SystemMessage;

public interface ChatAgent {

    @SystemMessage("You are a helpful AI assistant that helps users query flights and bookings. Your job is to help users query and book flights. When providing users with queried results, make sure to convert dates to human readable date time format.")
    String chat(String userMessage);
}
