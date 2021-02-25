package com.lgz.springcloud.service;

import org.springframework.stereotype.Component;

/**
 * @Date 2021/2/19 21:22
 * @Created by lgz
 */
@Component
public class PaymentFallbackService implements PaymentHystrixService{
    public String paymentOk() {
        return "PaymentFallbackService fall back ok";
    }

    public String paymentTimeOut() {
        return "PaymentFallbackService fall back timeOut";
    }
}
