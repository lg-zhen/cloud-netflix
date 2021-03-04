package com.lgz.springcloud.alibaba.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.lgz.springcloud.entities.CommonResult;
import com.lgz.springcloud.entities.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @Date 2021/3/4 23:30
 * @Created by lgz
 */
@RestController
@Slf4j
public class CircleBreakerController {
    public static final String SERVICE_URL = "http://nacos-payment-provider";

    @Resource
    private RestTemplate restTemplate;

    @GetMapping("/consumer/fallback/{id}")
//    @SentinelResource(value = "fallback")
    @SentinelResource(value = "fallback", fallback = "handlerFallback")
    public CommonResult<Payment> fallback(@PathVariable Long id) throws IllegalAccessException {
        CommonResult<Payment> result = restTemplate.getForObject(SERVICE_URL + "/paymentSQL/" + id, CommonResult.class, id);
        if (id == 4) {
            throw new IllegalAccessException("非法参数异常----->");
        } else {
            return result;
        }
    }

    // 本例是fallback
    public CommonResult<Payment> handlerFallback(@PathVariable Long id, Throwable e) {
        final Payment payment = new Payment(id, null);
        return new CommonResult<Payment>(444, "兜底异常: " + e.getMessage(), payment);
    }
}
