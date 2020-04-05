package com.lyx.ganon.admin.func.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/thread")
public class ThreadPoolMonitorController {

    @Autowired
    private ThreadPoolTaskExecutor taskExecutor;

    @PostMapping("/short_job")
    public String shotrJob() {
        taskExecutor.execute(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        return "OK";
    }

    @PostMapping("/long_job")
    public String longJob() {
        taskExecutor.execute(() -> {
            try {
                TimeUnit.SECONDS.sleep(15);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        return "OK";
    }

    @PostMapping("/clear")
    public String clear() {
        taskExecutor.getThreadPoolExecutor().getQueue().clear();
        return "OK";
    }

}
