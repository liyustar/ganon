package com.lyx.ganon.admin.controller;

import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.ReadContext;
import com.lyx.ganon.common.MetricsInvoker;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;

import java.util.UUID;
import java.util.concurrent.ExecutionException;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UserControllerIT {

    @Test
    public void createUser(@Autowired TestRestTemplate restTemplate) {
        String content = "{\"name\":\"" + UUID.randomUUID().toString().substring(0, 16)
                + "\", \"password\":\"" + UUID.randomUUID().toString().substring(0, 16)
                + "\"}";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        ResponseEntity<String> body = restTemplate.exchange("/users",
                HttpMethod.POST,
                new HttpEntity<>(content, headers),
                new ParameterizedTypeReference<String>() {
                });
        ReadContext context = JsonPath.parse(body.getBody());
        assertThat((int) context.read("$.code")).isEqualTo(200);
    }

    @Test
    public void createUserPerformance(@Autowired TestRestTemplate restTemplate) throws ExecutionException, InterruptedException {
        MetricsInvoker invoker = MetricsInvoker.createBuilder()
                .setInvokeTimes(10)
                .setThreadNum(2)
                .build();

        MetricsInvoker.Metrics metrics = invoker.invoke(() -> {
            String content = "{\"name\":\"" + UUID.randomUUID().toString().substring(0, 16)
                    + "\", \"password\":\"" + UUID.randomUUID().toString().substring(0, 16)
                    + "\"}";
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            ResponseEntity<String> body = restTemplate.exchange("/users",
                    HttpMethod.POST,
                    new HttpEntity<>(content, headers),
                    new ParameterizedTypeReference<String>() {
                    });
            ReadContext context = JsonPath.parse(body.getBody());
            assertThat((int) context.read("$.code")).isEqualTo(200);
            return body;
        });
        log.info("{}", metrics);
    }

}