package com.lgz.springcloud.dao;

import com.lgz.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @Date 2021/2/1 0:06
 * @Created by lgz
 */
@Mapper
public interface PaymentDao {

    int slaveCreate(Payment payment);

    Payment getSlavePaymentById(@Param("id") Long id);
}
