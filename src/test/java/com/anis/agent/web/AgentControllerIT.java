package com.anis.agent.web;

import com.anis.agent.agent.BacklogAgent;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
class AgentControllerIT {

    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private BacklogAgent backlogAgent;

    @Test
    void shouldCallAgentEndpoint() {
        when(backlogAgent.handle("Create a GitHub task for adding Docker support"))
                .thenReturn("Issue created successfully");

        webTestClient.post()
                .uri("/api/run")
                .bodyValue("Create a GitHub task for adding Docker support")
                .exchange()
                .expectStatus().isOk()
                .expectBody(String.class)
                .isEqualTo("Issue created successfully");
    }
}