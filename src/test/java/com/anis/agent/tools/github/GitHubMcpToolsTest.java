package com.anis.agent.tools.github;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.anyMap;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.anis.agent.mcp.McpHttpClient;

import reactor.core.publisher.Mono;

class GitHubMcpToolsTest {

    @Test
    void shouldCreateIssueThroughMcpTool() {
        McpHttpClient mcp = mock(McpHttpClient.class);

        when(mcp.callTool(eq("create_issue"), anyMap()))
                .thenReturn(Mono.just(Map.of("result", "ok")));

        GitHubMcpTools tools = new GitHubMcpTools(
                mcp,
                "AnisDALI",
                "lab_mcp_ai_agent_springboot"
        );

        String result = tools.createIssue(
                "Test issue title",
                "Test issue body"
        );

        assertTrue(result.contains("Issue created successfully"));

        verify(mcp, times(1)).callTool(eq("create_issue"), anyMap());
    }
}