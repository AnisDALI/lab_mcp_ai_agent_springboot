package com.anis.agent.service;

import org.springframework.stereotype.Service;

import com.anis.agent.agent.BacklogAgent;

@Service
public class AgentService {

    private final BacklogAgent backlogAgent;

    public AgentService(BacklogAgent backlogAgent) {
        this.backlogAgent = backlogAgent;
    }

    public String run(String prompt) {
        return backlogAgent.handle(prompt);
    }
}