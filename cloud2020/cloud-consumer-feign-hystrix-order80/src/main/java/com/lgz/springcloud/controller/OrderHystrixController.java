package com.lgz.springcloud.controller;

import com.lgz.springcloud.service.PaymentHystrixService;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Date 2021/2/18 22:50
 * @Created by lgz
 */
@RestController
@Slf4j
@DefaultProperties(defaultFallback = "paymentGlobalFallBackMethod")
public class OrderHystrixController {
    @Resource
    private PaymentHystrixService paymentHystrixService;

    @GetMapping("/consumer/payment/hystrix/ok")
    public String paymentOk() {
        return paymentHystrixService.paymentOk();
    }

    //    @HystrixCommand(fallbackMethod = "paymentTimeOutHandler", commandProperties = {
    //            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1000")
    //    })
    @HystrixCommand
    @GetMapping("/consumer/payment/hystrix/timeOut")
    public String paymentTimeOut() {
        int i = 10 / 0;
        return paymentHystrixService.paymentTimeOut();
    }

    public String paymentTimeOutHandler() {
        return "线程池: " + Thread.currentThread().getName() + " TimeOutHandler,端口80";
    }

    public String paymentGlobalFallBackMethod() {
        return "我是全局异常回滚方法";
    }
}
