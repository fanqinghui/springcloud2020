package com.fqh.cloud.order.service;

import com.fqh.cloud.order.service.impl.PaymentFeignServiceImpl;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author fqh
 * @Description: feign接口调用
 * @date 2020/10/19下午6:49
 */
@Service
@FeignClient(value = "CLOUD-PAYMENT-SERVICE",fallback = PaymentFeignServiceImpl.class)
public interface PaymentFeignService {
  /**
   * 通过主键查询单条数据
   * @param id 主键
   * @return 单条数据
   */
  @GetMapping("payment/hystrix/ok/get/{id}")
  String selectOneOK(@PathVariable("id") Long id);

  /**
   * 通过主键查询单条数据
   * @param id 主键
   * @return 单条数据
   */
  @GetMapping("payment/hystrix/error/get/{id}")
  String selectOneError(@PathVariable("id") Long id);
}
