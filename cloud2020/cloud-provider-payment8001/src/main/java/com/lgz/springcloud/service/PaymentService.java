package com.lgz.springcloud.service;

import com.lgz.springcloud.entities.Payment;

/**
 * @Date 2021/2/1 0:16
 * @Created by lgz
 */
public interface PaymentService {

    int create(Payment payment);

    Payment getPaymentById(Long id);
}
