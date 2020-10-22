package com.fqh.cloud.payment.service;

import com.fqh.cloud.entity.payment.Payment;
import java.util.List;

/**
 * (Payment)表服务接口
 *
 * @author makejava
 * @since 2020-09-28 13:49:34
 */
public interface PaymentService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    String queryById_ok(Long id);


    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    String queryById_error(Long id);

    /**
     * 测试服务熔断
     * @param id
     * @return
     */
    String paymentCircuitBreaker(Long id);
}