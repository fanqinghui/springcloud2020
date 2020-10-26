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
public class AliPaymentProviderApplication8001 {

  public static void main(String[] args) {
    SpringApplication.run(AliPaymentProviderApplication8001.class,args);
  }
}
