package com.fqh.cloud.order.controller;

import com.fqh.cloud.entity.payment.CommonResult;
import com.fqh.cloud.entity.payment.Payment;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author fqh
 * @Description:
 * @date 2020/9/28下午4:03
 */
@RestController
@Slf4j
@RequestMapping("consumer")
public class OrderController {

  public static final String Payment_URL="http://localhost:8001";
  private static final String Payment_server_URL="http://CLOUD-PAYMENT-SERVICE";

  @Autowired
  RestTemplate restTemplate;
  @Autowired
  DiscoveryClient discoveryClient;

  /**
   * 调用远程服务
   * @param payment
   * @return
   */
  @PostMapping("/payment/create")
  public CommonResult<Payment> create(Payment payment){
      log.info("consumer-createOrder,payment:{}",payment);
      return restTemplate.postForObject(Payment_server_URL+"/payment/create",payment, CommonResult.class);
  }

  @GetMapping("/payment/get/{id}")
  public CommonResult<Payment> getPayment(@PathVariable("id") Long id){
    log.info("consumerGetPayment,id:{}",id);
    return restTemplate.getForObject(Payment_server_URL+"/payment/get/"+id,CommonResult.class);
  }

  @GetMapping("/discovery")
  public Object discoveryList() {
    List<String> services = discoveryClient.getServices();
      for(String service:services){
        log.info(service);
      }

    List<ServiceInstance> instances = discoveryClient.getInstances("cloud-payment-service");
    for(ServiceInstance instance:instances){
      log.info(instance.getInstanceId()+"|"+instance.getPort()+"|"+instance.getUri());
    }

    return services;
  }

  //zipkin+sleuth链路追踪
  @GetMapping("/payment/zipkin")
  public String paymentZipkin(){
    String result = restTemplate
        .getForObject(Payment_server_URL + "/payment/zipkin", String.class);
    return result;
  }

}
