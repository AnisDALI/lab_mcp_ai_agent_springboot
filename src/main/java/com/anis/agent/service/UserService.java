package com.anis.agent.service;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Service;

import com.anis.agent.domain.User;

@Service
public class UserService {

    private final Map<UUID, User> users = new ConcurrentHashMap<>();

    public User create(String name) {
        User user = new User(UUID.randomUUID(), name);
        users.put(user.id(), user);
        return user;
    }

    public User getById(UUID id) {
        return users.get(id);
    }
}