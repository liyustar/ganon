package com.lyx.ganon.common;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.Random;
import java.util.concurrent.TimeUnit;

@Slf4j
class MetricsInvokerTest {

    @Test
    public void test() throws Exception {
        MetricsInvoker invoker = MetricsInvoker.createBuilder()
                .setInvokeTimes(10)
                .setInvokeInterval(0)
                .build();

        MetricsInvoker.Metrics metrics = invoker.invoke(() -> 1);
        log.info("{}", metrics);
    }

    @Test
    public void test2() throws Exception {
        MetricsInvoker invoker = MetricsInvoker.createBuilder()
                .setInvokeTimes(400)
                .setThreadNum(4)
                .build();

        Random r = new Random();
        MetricsInvoker.Metrics metrics = invoker.invoke(() -> {
            int t = r.nextInt(5) + 1;
            TimeUnit.MILLISECONDS.sleep(t);
            return 1;
        });
        log.info("{}", metrics);
    }

}