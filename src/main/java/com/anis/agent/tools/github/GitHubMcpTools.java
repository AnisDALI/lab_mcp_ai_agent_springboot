package com.anis.agent.tools.github;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.anis.agent.mcp.McpHttpClient;
import com.anis.agent.tools.AgentTool;

import dev.langchain4j.agent.tool.Tool;

@Component
public class GitHubMcpTools implements AgentTool {

    private final McpHttpClient mcp;
    private final String owner;
    private final String repo;

    public GitHubMcpTools(
            McpHttpClient mcp,
            @Value("${github.owner}") String owner,
            @Value("${github.repo}") String repo
    ) {
        this.mcp = mcp;
        this.owner = owner;
        this.repo = repo;
    }

    @Tool("Create a GitHub issue in the configured repository")
    public String createIssue(String title, String body) {
        Map<String, Object> result = mcp.callTool(
                "create_issue",
                Map.of(
                        "owner", owner,
                        "repo", repo,
                        "title", title,
                        "body", body
                )
        ).block();

        return "Issue created successfully: " + result;
    }
}