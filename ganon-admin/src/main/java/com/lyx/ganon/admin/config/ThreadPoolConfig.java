package com.lyx.ganon.admin.config;

import com.lyx.ganon.common.async.TimedThreadPoolTaskExecutor;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.Collections;
import java.util.concurrent.ThreadPoolExecutor;

@Configuration
public class ThreadPoolConfig {

    @Bean
    public ThreadPoolTaskExecutor taskExecutor(MeterRegistry registry) {
        int processors = Runtime.getRuntime().availableProcessors();
        ThreadPoolTaskExecutor taskExecutor = new TimedThreadPoolTaskExecutor(registry,
                "my.task.executor", Collections.emptyList());
        taskExecutor.setCorePoolSize(processors * 2 + 1);
        taskExecutor.setMaxPoolSize(processors * 4);
        taskExecutor.setAwaitTerminationSeconds(10);
        taskExecutor.setQueueCapacity(20);
        taskExecutor.setThreadNamePrefix("my-task-executor-");
        taskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.AbortPolicy());
        return taskExecutor;
    }

}
