package com.fqh.cloud.alibaba;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author fqh
 * @Description:
 * @date 2020/10/25下午6:03
 */
@SpringBootApplication
@EnableDiscoveryClient
public class AliPaymentConsumerApplication9001 {

  public static void main(String[] args) {
    SpringApplication.run(AliPaymentConsumerApplication9001.class,args);
  }
}
