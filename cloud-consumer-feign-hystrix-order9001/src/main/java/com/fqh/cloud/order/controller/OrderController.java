package com.fqh.cloud.order.controller;

import com.fqh.cloud.entity.payment.CommonResult;
import com.fqh.cloud.entity.payment.Payment;
import com.fqh.cloud.order.service.PaymentFeignService;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
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
@RequestMapping("consumer/hystrix")
//@DefaultProperties(defaultFallback = "gloabErrorHandler")//全局熔断
public class OrderController {

  @Autowired
  PaymentFeignService paymentService;

  @GetMapping("/payment/ok/get/{id}")
  public String getPayment(@PathVariable("id") Long id) {
    log.info("consumerGetPaymentByFeign,id:{}", id);
    return paymentService.selectOneOK(id);
  }

  @GetMapping("/payment/error/get/{id}")
  //方法精确配置熔断策略，也可以用通用的策略
 /* @HystrixCommand(fallbackMethod = "selectOneErrorHandler", commandProperties = {
      @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000")
  })*/
  //@HystrixCommand
  public String getPaymentError(@PathVariable("id") Long id) {
    log.info("consumerGetPaymentByFeign,id:{}", id);
  /*  try {
      int i = 1 / 0;
    } catch (Exception e) {
      log.error("异常捕获");
    }*/
    String s = paymentService.selectOneError(id);
    log.info("result:{}",s);
    return s;
  }

  /**
   * 9001兜底方法
   */
  public String selectOneErrorHandler(@PathVariable("id") Long id) {
    log.info("消费者9001selectOneErrorHandler兜底方法--->,id:{}", id);
    return "9001兜底方法---selectOneErrorHandler";
  }

  /**
   * 全局gloab处理Handler
   * @return
   */
  public String gloabErrorHandler(){
    return "全局熔断降级兜底方法";
  }

}
