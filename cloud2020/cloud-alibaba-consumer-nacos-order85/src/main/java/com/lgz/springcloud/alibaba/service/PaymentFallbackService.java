package com.lgz.springcloud.alibaba.service;

import com.lgz.springcloud.entities.CommonResult;
import com.lgz.springcloud.entities.Payment;
import org.springframework.stereotype.Component;

/**
 * @Date 2021/3/6 1:51
 * @Created by lgz
 */
@Component
public class PaymentFallbackService implements PaymentService {

    public CommonResult<Payment> paymentSQL(Long id) {
        return new CommonResult<Payment>(444, "服务降级返回,--paymentFallbackService", new Payment(id, "错误"));
    }
}
