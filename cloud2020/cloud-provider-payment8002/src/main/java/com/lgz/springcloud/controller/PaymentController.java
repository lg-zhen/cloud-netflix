package com.lgz.springcloud.controller;

import com.lgz.springcloud.entities.CommonResult;
import com.lgz.springcloud.entities.Payment;
import com.lgz.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @Date 2021/2/1 0:19
 * @Created by lgz
 */
@Slf4j
@RestController
@RequestMapping("/payment/")
public class PaymentController {
    @Resource
    private PaymentService paymentService;
    @Value("${server.port}")
    private String serverPort;

    @PostMapping("create")
    public CommonResult create(@RequestBody Payment payment) {
        final int result = paymentService.create(payment);
        log.info("***插入结果为：{}", result);
        if (result > 0) {
            return new CommonResult(200, "插入数据库成功->serverPort:"+serverPort, result);
        } else {
            return new CommonResult(444, "插入数据库失败->serverPort:"+serverPort, null);
        }
    }

    @GetMapping("get/{id}")
    public CommonResult getPaymentById(@PathVariable Long id) {
        final Payment payment = paymentService.getPaymentById(id);
        log.info("查询结果为:{}", payment);
        log.info("我是谁");
        if (payment != null) {
            return new CommonResult(200, "查询成功->serverPort:"+serverPort, payment);
        } else {
            return new CommonResult(444, "查询失败->serverPort:"+serverPort, null);
        }
    }
}
