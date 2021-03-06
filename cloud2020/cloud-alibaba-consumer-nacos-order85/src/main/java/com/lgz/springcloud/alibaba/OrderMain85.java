package com.lgz.springcloud.alibaba;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @Date 2021/3/4 23:27
 * @Created by lgz
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class OrderMain85 {
    public static void main(String[] args) {
        SpringApplication.run(OrderMain85.class, args);
    }
}
