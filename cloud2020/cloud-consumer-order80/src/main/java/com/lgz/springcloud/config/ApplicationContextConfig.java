package com.lgz.springcloud.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @Date 2021/2/1 23:14
 * @Created by lgz
 */
@Configuration
public class ApplicationContextConfig {

    @Bean
    @LoadBalanced // 开启负载均衡模式
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }
}
