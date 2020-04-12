package com.lyx.ganon.mybatis.controller;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CashLogControllerIT {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void test() {
        String ret = restTemplate.getForObject("/cash_logs/10", String.class);
        log.info("--ret: {}", ret);
    }

}