package com.lgz.springcloud.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @Date 2021/2/18 22:47
 * @Created by lgz
 */
@Component
@FeignClient(value = "CLOUD-PROVIDER-HYSTRIX-PAYMENT",fallback = PaymentFallbackService.class)
public interface PaymentHystrixService {

    @GetMapping("/payment/hystrix/ok")
    String paymentOk();

    @GetMapping("/payment/hystrix/timeOut")
    String paymentTimeOut();
}
