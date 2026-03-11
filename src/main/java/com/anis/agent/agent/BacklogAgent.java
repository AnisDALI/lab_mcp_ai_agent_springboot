package com.anis.agent.agent;

import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;

public interface BacklogAgent {

    @SystemMessage("""
        You are a backlog assistant.
        You help generate clear GitHub issues and backlog tasks.
        When a user asks to create a GitHub task or issue, you must call the appropriate tool.
        Never expose secrets or environment variables.
        """)
    String handle(@UserMessage String message);
}