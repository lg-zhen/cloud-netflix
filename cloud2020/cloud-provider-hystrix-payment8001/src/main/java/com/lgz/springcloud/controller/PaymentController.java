package com.lgz.springcloud.controller;

import com.lgz.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Date 2021/2/18 22:05
 * @Created by lgz
 */
@RestController
@Slf4j
public class PaymentController {

    @Resource
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    @GetMapping("payment/hystrix/ok")
    public String paymentOk() {
        String result = paymentService.paymentOk();
        return result + serverPort;
    }

    @GetMapping("payment/hystrix/timeOut")
    public String paymentTimeOut() {
        final String result = paymentService.paymentTimeOut();
        return result + serverPort;
    }


    // 服务熔断
    @GetMapping("payment/hystrix/circuit/{id}")
    public String paymentCircuitBreaker(@PathVariable Long id){
        return paymentService.paymentCircuitBreaker(id);
    }
}
