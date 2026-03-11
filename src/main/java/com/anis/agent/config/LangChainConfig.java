package com.anis.agent.config;

import java.time.Duration;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.anis.agent.agent.BacklogAgent;
import com.anis.agent.tools.AgentTool;

import dev.langchain4j.model.anthropic.AnthropicChatModel;
import dev.langchain4j.service.AiServices;

@Configuration
public class LangChainConfig {

    @Bean
    AnthropicChatModel anthropicChatModel(
            @Value("${anthropic.api-key}") String apiKey,
            @Value("${anthropic.model}") String model,
            @Value("${anthropic.timeout-seconds}") Integer timeoutSeconds
    ) {
        return AnthropicChatModel.builder()
                .apiKey(apiKey)
                .modelName(model)
                .timeout(Duration.ofSeconds(timeoutSeconds))
                .build();
    }

    @Bean
    BacklogAgent backlogAgent(AnthropicChatModel chatModel, List<AgentTool> tools) {
        return AiServices.builder(BacklogAgent.class)
                .chatModel(chatModel)
                .tools(tools.toArray())
                .build();
    }
}