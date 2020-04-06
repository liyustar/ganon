package com.lyx.ganon.mybatis.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.ReadContext;
import com.lyx.ganon.mybatis.model.BizUser;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

@Slf4j
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UserControllerIT {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void bigIntergationTest() throws JsonProcessingException {
        restTemplate.delete("/users");

        // create user
        BizUser user = new BizUser();
        user.setName("user01");
        user.setPassword("user01");
        String content = new ObjectMapper().writeValueAsString(user);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        String ret = restTemplate.postForObject("/users", new HttpEntity<>(content, headers), String.class);
        log.info("--ret: {}", ret);
        ReadContext context = JsonPath.parse(ret);
        int userId = context.read("$.data");
//        assertThat(userId).isNotNull();
        log.info("--userId: {}", userId);


        user.setName("user02");
        content = new ObjectMapper().writeValueAsString(user);
        ret = restTemplate.postForObject("/users", new HttpEntity<>(content, headers), String.class);
        log.info("--ret: {}", ret);
        context = JsonPath.parse(ret);
        userId = context.read("$.data");
//        assertThat(userId).isNotNull();
        log.info("--userId: {}", userId);
    }

}