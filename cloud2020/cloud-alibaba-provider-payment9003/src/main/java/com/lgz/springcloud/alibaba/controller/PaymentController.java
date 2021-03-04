package com.lgz.springcloud.alibaba.controller;

import com.lgz.springcloud.entities.CommonResult;
import com.lgz.springcloud.entities.Payment;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

/**
 * @Date 2021/3/4 23:12
 * @Created by lgz
 */
@RestController
public class PaymentController {
    @Value("${server.port}")
    private String serverPort;
    public static HashMap<Long, Payment> hashMap = new HashMap<Long, Payment>();

    static {
        hashMap.put(1L, new Payment(1L, "1"));
        hashMap.put(2L, new Payment(2L, "2"));
        hashMap.put(3L, new Payment(3L, "3"));
    }

    @GetMapping("/paymentSQL/{id}")
    public CommonResult<Payment> paymentSQL(@PathVariable Long id) {
        final Payment payment = hashMap.get(id);
        return new CommonResult<Payment>(200, String.format("from sql, serverPort:%s", serverPort), payment);
    }
}
