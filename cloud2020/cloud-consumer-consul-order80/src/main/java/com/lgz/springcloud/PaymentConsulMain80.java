package com.lgz.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @Date 2021/2/6 23:45
 * @Created by lgz
 */
@SpringBootApplication
@EnableDiscoveryClient
public class PaymentConsulMain80 {
    public static void main(String[] args) {
        SpringApplication.run(PaymentConsulMain80.class, args);
    }
}
