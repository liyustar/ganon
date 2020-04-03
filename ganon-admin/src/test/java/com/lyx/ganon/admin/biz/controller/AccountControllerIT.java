package com.lyx.ganon.admin.biz.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.ReadContext;
import com.lyx.ganon.admin.biz.controller.request.AddAccountVO;
import com.lyx.ganon.admin.biz.model.BizAccount;
import com.lyx.ganon.admin.controller.ResponseObj;
import com.lyx.ganon.common.MetricsInvoker;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AccountControllerIT {

    @Test
    void addAccount(@Autowired TestRestTemplate restTemplate) throws JsonProcessingException {
        AddAccountVO vo = new AddAccountVO();
        vo.setAccCode("test1");
        vo.setAmt(10.0);
        String content = new ObjectMapper().writeValueAsString(vo);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        ResponseEntity<String> body = restTemplate.exchange("/biz/accounts",
                HttpMethod.POST,
                new HttpEntity<>(content, headers),
                new ParameterizedTypeReference<String>() {
                });
        ReadContext context = JsonPath.parse(body.getBody());
        assertThat((int) context.read("$.code")).isEqualTo(200);
    }

    @Test
    void addAccountPerformance(@Autowired TestRestTemplate restTemplate) throws JsonProcessingException, ExecutionException, InterruptedException {
        // Metrics{total=1000, success=1000, fail=0, start=1585816067811, end=1585816096941, period=29130, tps=34.32887058015791, latency_avg=577.238, latency_90=621, latency_95=733, latency_99=1989}

        // Metrics{total=1000, success=997, fail=3, start=1585816897469, end=1585816901831, period=4362, tps=229.25263640531867, latency_avg=84.894, latency_90=109, latency_95=166, latency_99=573}

        // Metrics{total=2000, success=1997, fail=3, start=1585817020750, end=1585817028658, period=7908, tps=252.90844714213455, latency_avg=752.413, latency_90=1230, latency_95=1388, latency_99=1978}
        MetricsInvoker invoker = MetricsInvoker.createBuilder()
                .setInvokeTimes(4000)
                .setThreadNum(200)
                .build();

        MetricsInvoker.Metrics metrics = invoker.invoke(() -> {
            AddAccountVO vo = new AddAccountVO();
            vo.setAccCode(UUID.randomUUID().toString().substring(0, 16));
            vo.setAmt(Math.random() * 100000000);
            String content = new ObjectMapper().writeValueAsString(vo);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            ResponseEntity<ResponseObj<Integer>> body = restTemplate.exchange("/biz/accounts",
                    HttpMethod.POST,
                    new HttpEntity<>(content, headers),
                    new ParameterizedTypeReference<ResponseObj<Integer>>() {
                    });
            return body;
        }, (t) -> t.getBody().getCode() == 200 && t.getBody().getData().equals(1));
        log.info("{}", metrics);
    }

    private static ThreadLocal<Random> random = ThreadLocal.withInitial(Random::new);

    @Test
    void incrAmtPerformance(@Autowired TestRestTemplate restTemplate) throws JsonProcessingException, ExecutionException, InterruptedException {
        // 100
        // Metrics{total=4000, success=4000, fail=0, start=1585822299508, end=1585822310160, period=10652, tps=375.51633496057076, latency_avg=496.97725, latency_90=790, latency_95=891, latency_99=1176}

        // 10
        // Metrics{total=4000, success=4000, fail=0, start=1585822399374, end=1585822411257, period=11883, tps=336.6153328284103, latency_avg=565.30875, latency_90=853, latency_95=947, latency_99=1133}

        int bound = 10;
        List<BizAccount> accounts = getAccounts(restTemplate, bound);
        MetricsInvoker invoker = MetricsInvoker.createBuilder()
                .setInvokeTimes(4000)
                .setThreadNum(200)
                .build();

        MetricsInvoker.Metrics metrics = invoker.invoke(() -> {
            int index = random.get().nextInt(bound);
            String accCode = accounts.get(index).getAccCode();
            String content = "incrAmt=" + Math.round(Math.random() * 1000000) / 100.0;
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
            ResponseEntity<ResponseObj<Integer>> body = restTemplate.exchange("/biz/accounts/" + accCode + "/incr_amt",
                    HttpMethod.PUT,
                    new HttpEntity<>(content, headers),
                    new ParameterizedTypeReference<ResponseObj<Integer>>() {
                    });
            return body;
        }, (t) -> t.getBody().getCode() == 200 && t.getBody().getData().equals(1));
        log.info("{}", metrics);
    }


    private List<BizAccount> getAccounts(TestRestTemplate restTemplate, int size) {
        ArrayList<BizAccount> accountList = Lists.newArrayListWithCapacity(size);
        int step = 500;
        for (int i = 1; i <= size; i += step) {
            ResponseEntity<ResponseObj<List<BizAccount>>> body = restTemplate.exchange(String.format("/biz/accounts/%d/to/%d", i, i + step - 1),
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<ResponseObj<List<BizAccount>>>() {
                    });
            accountList.addAll(body.getBody().getData());
        }
        return accountList;
    }
}