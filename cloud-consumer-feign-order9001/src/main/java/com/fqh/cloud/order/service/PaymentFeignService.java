package com.fqh.cloud.order.service;

import com.fqh.cloud.entity.payment.CommonResult;
import com.fqh.cloud.entity.payment.Payment;
import javax.xml.ws.ServiceMode;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author fqh
 * @Description: feign接口调用
 * @date 2020/10/19下午6:49
 */
@Service
@FeignClient(value = "CLOUD-PAYMENT-SERVICE")
public interface PaymentFeignService {
  /**
   * 通过主键查询单条数据
   * @param id 主键
   * @return 单条数据
   */
  @GetMapping("/payment/get/{id}")
   CommonResult<Payment> selectOne(@PathVariable("id") Long id);

  @PostMapping("/payment/create")
  CommonResult<Payment> create(@RequestBody Payment payment);
}
