package com.fqh.cloud.alibaba.controller;

import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author fqh
 * @Description:
 * @date 2020/10/25下午6:11
 */
@RestController
public class OrderController {

  @Value("${server.port}")
  private String serverPort;
  @Autowired
  private RestTemplate restTemplate;

  @Value("${service-url.nacos-payment-service}")
  private String serverUrl;


  @GetMapping("/consumer/order/get/{id}")
  public String getPayment(@PathVariable("id") Integer id){
    return restTemplate.getForObject(serverUrl+"/payment/nacos/get/"+id,String.class);
  }

}
