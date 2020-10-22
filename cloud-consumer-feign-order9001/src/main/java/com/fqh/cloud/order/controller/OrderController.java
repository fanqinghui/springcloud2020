package com.fqh.cloud.order.controller;

import com.fqh.cloud.entity.payment.CommonResult;
import com.fqh.cloud.entity.payment.Payment;
import com.fqh.cloud.order.service.PaymentFeignService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author fqh
 * @Description:
 * @date 2020/9/28下午4:03
 */
@RestController
@Slf4j
@RequestMapping("consumer/feign")
public class OrderController {

  @Autowired
  PaymentFeignService paymentService;
  /**
   * 调用远程服务
   * @param payment
   * @return
   */
  @PostMapping("/payment/create")
  public CommonResult<Payment> create(Payment payment){
      log.info("consumer-createOrderByFeign,payment:{}",payment);
      return paymentService.create(payment);
  }

  @GetMapping("/payment/get/{id}")
  public CommonResult<Payment> getPayment(@PathVariable("id") Long id){
    log.info("consumerGetPaymentByFeign,id:{}",id);
    return paymentService.selectOne(id);
  }

  @GetMapping("/payment/timeout/get/{id}")
  public CommonResult<Payment> getPaymentTimeout(@PathVariable("id") Long id)
      throws InterruptedException {
    log.info("consumerGetPaymentByFeign,id:{}",id);
    Thread.sleep(5300L);
    return paymentService.selectOne(id);
  }
}
