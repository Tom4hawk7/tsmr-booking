package tsmr.booking.ai;

import dev.langchain4j.memory.ChatMemory;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.model.openai.OpenAiChatModel;
import dev.langchain4j.service.AiServices;
import org.springframework.stereotype.Service;

@Service
public class BookingAssistant {

    private final ChatAgent chatAgent;

    public BookingAssistant(BookingTools tools) {
        var model = OpenAiChatModel.builder()
                .apiKey(System.getenv("OPENAI_API_KEY"))
                .modelName("gpt-3.5-turbo")
                .build();

        ChatMemory memory = MessageWindowChatMemory.withMaxMessages(20);

        this.chatAgent = AiServices.builder(ChatAgent.class)
                .chatLanguageModel(model)
                .tools(tools)
                .chatMemory(memory)
                .build();
    }

    public String chat(String message) {
        return chatAgent.chat(message);
    }
}
