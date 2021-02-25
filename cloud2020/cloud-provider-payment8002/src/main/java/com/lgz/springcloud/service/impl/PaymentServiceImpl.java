package com.lgz.springcloud.service.impl;

import com.lgz.springcloud.dao.PaymentDao;
import com.lgz.springcloud.entities.Payment;
import com.lgz.springcloud.service.PaymentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Date 2021/2/1 0:16
 * @Created by lgz
 */
@Service
public class PaymentServiceImpl implements PaymentService {
    @Resource
    private PaymentDao paymentDao;

    public int create(Payment payment) {
        return paymentDao.slaveCreate(payment);
    }

    public Payment getPaymentById(Long id) {
        return paymentDao.getSlavePaymentById(id);
    }
}
