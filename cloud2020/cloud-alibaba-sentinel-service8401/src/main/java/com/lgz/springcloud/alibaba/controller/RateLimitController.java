package com.lgz.springcloud.alibaba.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.lgz.springcloud.alibaba.handler.CustomerHandler;
import com.lgz.springcloud.entities.CommonResult;
import com.lgz.springcloud.entities.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Date 2021/3/4 22:28
 * @Created by lgz
 */
@RestController
@Slf4j
public class RateLimitController {

    @GetMapping("/byResource")
    @SentinelResource(value = "byResource", blockHandler = "handleException")
    public CommonResult<Payment> byResource() {
        return new CommonResult<Payment>(200, "按资源名称限流测试ok", new Payment(2020L, "serial001"));
    }

    public CommonResult<Payment> handleException(BlockException exception) {
        return new CommonResult<Payment>(444, exception.getMessage());
    }

    @GetMapping("/byUrl")
    @SentinelResource(value = "byUrl")
    public CommonResult<Payment> byUrl() {
        return new CommonResult<Payment>(200, "按URL限流测试ok", new Payment(2020L, "serial002"));
    }

    @GetMapping("/customerHandler")
    @SentinelResource(value = "customerHandler",blockHandlerClass = CustomerHandler.class,blockHandler = "handlerException1")
    public CommonResult<Payment> customerHandler() {
        return new CommonResult<Payment>(200, "按客户自定义限流测试ok", new Payment(2020L, "serial003"));
    }
}
