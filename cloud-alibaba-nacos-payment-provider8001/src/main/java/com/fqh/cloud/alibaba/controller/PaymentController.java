package com.fqh.cloud.alibaba.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author fqh
 * @Description:
 * @date 2020/10/25下午6:11
 */
@RestController
public class PaymentController {

  @Value("${server.port}")
  private String serverPort;

  @GetMapping("/payment/nacos/get/{id}")
  public String getPayment(@PathVariable("id") Integer id){
    return "nacos registry,serverPort:"+serverPort+"\t id:"+id;
  }

}
