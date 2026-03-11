package com.anis.agent.web;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
class UserControllerIT {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void createAndFetchUser() {
        String responseBody = webTestClient.post()
                .uri("/api/users?name=Alice")
                .exchange()
                .expectStatus().isOk()
                .expectBody(String.class)
                .returnResult()
                .getResponseBody();

        String id = extractId(responseBody);

        webTestClient.get()
                .uri("/api/users/" + id)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.name").isEqualTo("Alice");
    }

    private String extractId(String json) {
        int start = json.indexOf("\"id\":\"") + 6;
        int end = json.indexOf("\"", start);
        return json.substring(start, end);
    }
}