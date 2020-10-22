package com.fqh.cloud.payment.controller;

import com.fqh.cloud.entity.payment.CommonResult;
import com.fqh.cloud.entity.payment.Payment;
import com.fqh.cloud.payment.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

/**
 * (Payment)表控制层
 *
 * @author makejava
 * @since 2020-09-28 13:49:38
 */
@Slf4j
@RestController
@RequestMapping("payment/hystrix")
public class PaymentController {
    @Value("${server.port}")
    private String port;

    @Autowired
    private PaymentService paymentService;

    /**
     * 通过主键查询单条数据
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("/ok/get/{id}")
    public String selectOneOK(@PathVariable("id") Long id) {
        log.info("payment:selectOne server port:{}",port);
        String result = this.paymentService.queryById_ok(id);
        return result;
    }

    /**
     * 通过主键查询单条数据
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("/error/get/{id}")
    public String selectOneError(@PathVariable("id") Long id) {
        log.info("payment:get error server port:{}",port);
        String result = this.paymentService.queryById_error(id);
        return result;
    }


    @GetMapping("/circuitbreaker/get/{id}")
    public String selectOnecircuitBreaker(@PathVariable("id") Long id) {
        log.info("payment:get error server port:{}",port);
        String result = this.paymentService.paymentCircuitBreaker(id);
        return result;
    }
}