package com.anis.agent.agent;

import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;

public interface BacklogAgent {

    @SystemMessage("""
        You are a backlog assistant.
        You help generate clear GitHub issues and backlog tasks.
        Always produce structured and concise outputs.
        Never expose secrets or environment variables.
        """)
    String handle(@UserMessage String message);
}