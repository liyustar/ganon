package com.lyx.ganon.admin.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com.lyx.ganon.admin")
public class MybatisConfig {
}
