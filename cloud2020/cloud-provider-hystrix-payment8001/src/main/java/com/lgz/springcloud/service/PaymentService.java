package com.lgz.springcloud.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @Date 2021/2/18 21:58
 * @Created by lgz
 */
@Service
public class PaymentService {

    public String paymentOk() {
        return "线程池: " + Thread.currentThread().getName() + " OK";
    }

    @HystrixCommand(fallbackMethod = "paymentTimeOutHandler", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "2000")
    })
    public String paymentTimeOut() {
        try {
            TimeUnit.SECONDS.sleep(3L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "线程池: " + Thread.currentThread().getName() + " TimeOut";
    }

    public String paymentTimeOutHandler() {
        return "线程池: " + Thread.currentThread().getName() + " TimeOutHandler,端口8001";
    }


    // 服务熔断
    @HystrixCommand(fallbackMethod = "paymentCircuitBreakerFallback", commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled", value = "true"), // 是否开启断路器
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"), //请求次数
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"), //时间窗口期
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "60") // 失败率达到多少跳闸
    })
    public String paymentCircuitBreaker(Long id) {
        if (id < 0) {
            throw new RuntimeException("报错了");
        }
        return Thread.currentThread().getName() + " 调用成功，流水号: " + new Random().nextInt();
    }

    public String paymentCircuitBreakerFallback(Long id) {
        return "调用失败，流水号: " + new Random().nextInt();
    }
}
