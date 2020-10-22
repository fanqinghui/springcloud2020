package com.fqh.cloud.order.controller;

import com.fqh.cloud.entity.payment.CommonResult;
import com.fqh.cloud.entity.payment.Payment;
import com.fqh.cloud.order.loadBalancer.LoadBalancer;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
  @Autowired
  LoadBalancer loadBalancer;

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

  /**
   * 自定义ribbon的负载均衡策略为：random--详细见MyRibbonRule类与OrderApplication启动类的ribbon配置
   * @param id
   * @return
   */
  @GetMapping("/payment/ribbon/randomGet/{id}")
  public CommonResult<Payment> getPaymentByRandomGet(@PathVariable("id") Long id){
    log.info("consumerGetPaymentByRandomGet_,id:{}",id);
    return restTemplate.getForObject(Payment_server_URL+"/payment/get/"+id,CommonResult.class);
  }

  /**
   * 自定义ribbon的负载均衡策略为：random--详细见MyRibbonRule类与OrderApplication启动类的ribbon配置
   * @param id
   * @return
   */
  @GetMapping("/payment/ribbon/randomGet2/{id}")
  public CommonResult<Payment> getPaymentByRandomGet2(@PathVariable("id") Long id){
    log.info("consumerGetPaymentByRandomGet_,id:{}",id);
    ResponseEntity<CommonResult> responseEntity = restTemplate
        .getForEntity(Payment_server_URL + "/payment/get/" + id, CommonResult.class);
    if(responseEntity.getStatusCode().is2xxSuccessful()){
      return responseEntity.getBody();
    }else{
      return new CommonResult<>(404,"查询不到记录");
    }
  }

  /**
   * 自定义loadBalance访问方法
   * 重要--需要用自定义的负载均衡，需要把RestTemplateConfig 的getRestTemplate方法的@LoadBalance注解注释掉
   * @param id
   * @return
   */
  @GetMapping("/payment/selfBalance/get/{id}")
  public CommonResult<Payment> getPaymentBySelfLoadBalance(@PathVariable("id") Long id){
    log.info("consumerGetPaymentBySelfLoadBalance_,id:{}",id);
    List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
    if(CollectionUtils.isEmpty(instances)){
      return null;
    }
    ServiceInstance serviceInstance = loadBalancer.instances(instances);
    System.out.println("selfBlance:"+serviceInstance.getUri());
    String url=serviceInstance.getUri()+"/payment/get/"+id;
    System.out.println(url);
    return restTemplate.getForObject(url,CommonResult.class);
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
}
