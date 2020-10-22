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
@RequestMapping("payment")
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
    @GetMapping("/get/{id}")
    public CommonResult<Payment> selectOne(@PathVariable("id") Long id) {
        log.info("payment:selectOne server port:{}",port);
        Payment payment = this.paymentService.queryById(id);
        if(payment!=null){
            return new CommonResult(200,"查询成功"+port,payment);
        }else{
            return new CommonResult(404,"没有找到该记录");
        }
    }

    @PostMapping("/create")
    public CommonResult<Payment> create(@RequestBody Payment payment){
        try{
            log.info("payment:create server port:{}",port);
            log.info("create payment:{}",payment);
            paymentService.insert(payment);
            return new CommonResult(200,"插入成功"+port,payment);
        }catch (Exception e){
            return new CommonResult(444,"插入失败",payment);
        }
    }

}