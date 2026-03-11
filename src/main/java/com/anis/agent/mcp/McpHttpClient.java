package com.anis.agent.mcp;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;

@Component
public class McpHttpClient {

    private final WebClient webClient;
    private final String mcpPath;

    public McpHttpClient(
            WebClient.Builder webClientBuilder,
            @Value("${mcp.base-url}") String baseUrl,
            @Value("${mcp.path}") String mcpPath
    ) {
        this.webClient = webClientBuilder.baseUrl(baseUrl).build();
        this.mcpPath = mcpPath;
    }

    public Mono<Map> listTools() {
        Map<String, Object> body = Map.of(
                "jsonrpc", "2.0",
                "id", "1",
                "method", "tools/list",
                "params", Map.of()
        );

        return webClient.post()
                .uri(mcpPath)
                .bodyValue(body)
                .retrieve()
                .bodyToMono(Map.class);
    }

    public Mono<Map> callTool(String toolName, Map<String, Object> arguments) {
        Map<String, Object> body = Map.of(
                "jsonrpc", "2.0",
                "id", "1",
                "method", "tools/call",
                "params", Map.of(
                        "name", toolName,
                        "arguments", arguments
                )
        );

        return webClient.post()
                .uri(mcpPath)
                .bodyValue(body)
                .retrieve()
                .bodyToMono(Map.class);
    }
}