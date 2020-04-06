package com.lyx.ganon.admin.config;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.RetryOneTime;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.zookeeper.lock.ZookeeperLockRegistry;

@Configuration
@ConditionalOnProperty("zk.connectString")
public class GlobalLockConfig {

    @Value("${zk.connectString}")
    private String connectString;

    @Value(("${zk.rootPath}"))
    private String rootPath;

    @Bean
    public CuratorFramework curatorFramework() {
        CuratorFramework client =  CuratorFrameworkFactory.newClient(connectString, new RetryOneTime(100));
        client.start();
        return client;
    }

    @Bean
    public ZookeeperLockRegistry zookeeperLockRegistry(CuratorFramework curatorFramework) {
        return new ZookeeperLockRegistry(curatorFramework, rootPath);
    }
}
