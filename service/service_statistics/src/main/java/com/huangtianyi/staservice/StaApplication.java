package com.huangtianyi.staservice;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@ComponentScan(basePackages = {"com.huangtianyi"})
@EnableDiscoveryClient
@EnableFeignClients
@MapperScan("com.huangtianyi.staservice.mapper")
@EnableScheduling
public class StaApplication {

    public static void main(String[] args) {
        SpringApplication.run(StaApplication.class, args);
    }
}
