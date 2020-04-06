package com.lyx.ganon.admin.func.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.zookeeper.lock.ZookeeperLockRegistry;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

/**
 * @author liyuxing
 */
@RestController
@RequestMapping("/func/thread")
public class ThreadPoolMonitorController {

    @Autowired
    private ThreadPoolTaskExecutor taskExecutor;

    @Autowired(required = false)
    private ZookeeperLockRegistry lockRegistry;

    @PostMapping("/abort")
    public void abort() {
        throw new RuntimeException("abort");
    }

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
        if (lockRegistry == null) {
            taskExecutor.getThreadPoolExecutor().getQueue().clear();
            return "OK";
        }
        Lock lock = lockRegistry.obtain("test");
        try {
            if (lock.tryLock(10, TimeUnit.SECONDS)) {
                try {
                    taskExecutor.getThreadPoolExecutor().getQueue().clear();
                } finally {
                    lock.unlock();
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "OK";
    }

}
