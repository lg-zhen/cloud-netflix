package com.lgz.springcloud.controller;

import com.lgz.springcloud.entities.CommonResult;
import com.lgz.springcloud.entities.Payment;
import com.lgz.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

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
    @Resource
    private DiscoveryClient discoveryClient;

    @PostMapping("create")
    public CommonResult create(@RequestBody Payment payment) {
        final int result = paymentService.create(payment);
        log.info("***插入结果为：{}", result);
        if (result > 0) {
            return new CommonResult(200, "插入数据库成功->serverPort:" + serverPort, result);
        } else {
            return new CommonResult(444, "插入数据库失败->serverPort:" + serverPort, null);
        }
    }

    @GetMapping("get/{id}")
    public CommonResult getPaymentById(@PathVariable Long id) {
        final Payment payment = paymentService.getPaymentById(id);
        log.info("查询结果为:{}", payment);
        log.info("我是谁");
        if (payment != null) {
            return new CommonResult(200, "查询成功->serverPort:" + serverPort, payment);
        } else {
            return new CommonResult(444, "查询失败->serverPort:" + serverPort, null);
        }
    }

    @GetMapping("discovery")
    public Object discovery() {
        List<String> services = discoveryClient.getServices();
        for (String service : services) {
            log.info("element:" + service);
        }
        final List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        for (ServiceInstance instance : instances) {
            log.info(instance.getServiceId() + "\t" + instance.getHost() + "\t" + instance.getPort() + "\t" + instance.getUri());
        }
        return this.discoveryClient;
    }

    @GetMapping("feign/timeout")
    public String timeout(){
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return serverPort;
    }
}
