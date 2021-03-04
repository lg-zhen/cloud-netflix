package com.lgz.springcloud.alibaba.handler;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.lgz.springcloud.entities.CommonResult;
import com.lgz.springcloud.entities.Payment;

/**
 * @Date 2021/3/4 22:51
 * @Created by lgz
 */
public class CustomerHandler {

    public static CommonResult<Payment> handlerException1(BlockException exception) {
        return new CommonResult<Payment>(4444, "按客户自定义全局处理异常---1");
    }

    public static CommonResult<Payment> handlerException2(BlockException exception) {
        return new CommonResult<Payment>(4444, "按客户自定义全局处理异常---2");
    }
}
