package com.lyx.ganon.admin.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import com.google.common.collect.Queues;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.ReadContext;
import com.lyx.ganon.admin.model.SysUser;
import com.lyx.ganon.common.MetricsInvoker;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;

import java.util.*;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutionException;
import java.util.function.Function;
import java.util.stream.Collectors;

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


    @Test
    public void queryUser(@Autowired TestRestTemplate restTemplate) throws ExecutionException, InterruptedException {
        BlockingQueue<Integer> queue = Queues.newArrayBlockingQueue(500);
        for (int i = 1; i <= 500; i++) {
            queue.add(i);
        }
        int step = 100;

        // 单线程, 慢网络
        // Metrics{total=500, success=500, fail=0, start=1585755748142, end=1585755799554, period=51412, tps=9.725355948027698, latency_avg=102.822, latency_90=116, latency_95=124, latency_99=193}

        // 20线程, 慢网络
        // Metrics{total=500, success=500, fail=0, start=1585755680559, end=1585755688742, period=8183, tps=61.10228522546743, latency_avg=322.442, latency_90=511, latency_95=625, latency_99=975}

        MetricsInvoker invoker = MetricsInvoker.createBuilder()
                .setInvokeTimes(500)
                .setThreadNum(20)
                .build();
        MetricsInvoker.Metrics metrics = invoker.invoke(() -> {
            Integer start = queue.take();
            ResponseEntity<String> body = restTemplate.exchange(String.format("/users/%d/to/%d", start, start + step - 1),
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<String>() {
                    });
            ReadContext context = JsonPath.parse(body.getBody());
            assertThat((int) context.read("$.code")).isEqualTo(200);
            return body;
        });
        log.info("{}", metrics);
    }

    @Test
    public void queryUserByNamePerformance(@Autowired TestRestTemplate restTemplate) throws ExecutionException, InterruptedException {
        BlockingQueue<List<SysUser>> userQueue = getUsers(restTemplate);

        // 20线程, 慢网络
        // Metrics{total=500, success=500, fail=0, start=1585755131905, end=1585755151867, period=19962, tps=25.047590421801424, latency_avg=777.956, latency_90=986, latency_95=1306, latency_99=2855}
        MetricsInvoker invoker = MetricsInvoker.createBuilder()
                .setInvokeTimes(500)
                .setThreadNum(20)
                .build();
        MetricsInvoker.Metrics metrics = invoker.invoke(() -> {
            List<SysUser> users = userQueue.take();
            List<String> names = users.stream().map(SysUser::getName).collect(Collectors.toList());
            String content = new ObjectMapper().writeValueAsString(names);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            ResponseEntity<String> body = restTemplate.exchange("/users/byname",
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

    @Test
    public void queryUserByIdPerformance(@Autowired TestRestTemplate restTemplate) throws ExecutionException, InterruptedException {
        BlockingQueue<List<SysUser>> userQueue = getUsers(restTemplate);

        // 20线程, 慢网络
        // Metrics{total=500, success=500, fail=0, start=1585754816274, end=1585754822248, period=5974, tps=83.69601606963508, latency_avg=233.004, latency_90=318, latency_95=360, latency_99=507}
        MetricsInvoker invoker = MetricsInvoker.createBuilder()
                .setInvokeTimes(500)
                .setThreadNum(20)
                .build();
        MetricsInvoker.Metrics metrics = invoker.invoke(() -> {
            List<SysUser> users = userQueue.take();
            List<Integer> ids = users.stream().map(SysUser::getId).collect(Collectors.toList());
            String content = new ObjectMapper().writeValueAsString(ids);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            ResponseEntity<String> body = restTemplate.exchange("/users/byid",
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

    private BlockingQueue<List<SysUser>> getUsers(TestRestTemplate restTemplate) {
        ArrayList<SysUser> userList = Lists.newArrayListWithCapacity(50000);
        int step = 500;
        int total = 50000;
        for (int i = 1; i <= total; i += step) {
            ResponseEntity<ResponseObj<List<SysUser>>> body = restTemplate.exchange(String.format("/users/%d/to/%d", i, i + step - 1),
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<ResponseObj<List<SysUser>>>() {
                    });
            userList.addAll(body.getBody().getData());
        }
        Map<String, SysUser> userMap = userList.stream().collect(Collectors.toMap(SysUser::getName, Function.identity()));
        Iterator<SysUser> iterator = userMap.values().iterator();

        int queryStep = 100;
        BlockingQueue<List<SysUser>> queue = Queues.newArrayBlockingQueue(total / queryStep);
        for (int i = 1; i <= total; i += queryStep) {
            List<SysUser> childList = Lists.newArrayListWithCapacity(queryStep);
            for (int j = 0; j < queryStep; j++) {
                childList.add(iterator.next());
            }
            queue.add(childList);
        }
        return queue;
    }


}