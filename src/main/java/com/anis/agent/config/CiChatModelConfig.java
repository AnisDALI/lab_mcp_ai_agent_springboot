package com.anis.agent.config;

import dev.langchain4j.data.message.AiMessage;
import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.chat.request.ChatRequest;
import dev.langchain4j.model.chat.response.ChatResponse;
import dev.langchain4j.model.output.TokenUsage;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Profile("ci")
@Configuration
public class CiChatModelConfig {

    @Bean
    ChatModel ciChatModel() {
        return new ChatModel() {
            @Override
            public ChatResponse chat(ChatRequest chatRequest) {
                return ChatResponse.builder()
                        .aiMessage(AiMessage.from("CI OK"))
                        .tokenUsage(new TokenUsage(0, 0))
                        .build();
            }
        };
    }
}