package com.fqh.cloud.payment.controller;

import com.fqh.cloud.entity.payment.CommonResult;
import com.fqh.cloud.entity.payment.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * (Payment)表控制层
 * @author makejava
 * @since 2020-09-28 13:49:38
 */
@Slf4j
@RestController
@RequestMapping("payment")
public class PaymentController {
    @Value("${server.port}")
    private String port;

    /**
     * 通过主键查询单条数据
     */
    @GetMapping("/zk")
    public CommonResult<Payment> paymentZk() {
        log.info("payment:ZK port:{}",port);
        return new CommonResult(200,"paymentZk成功:",port);
    }

}