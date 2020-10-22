package com.fqh.cloud.payment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author fqh
 * @Description:
 * @date 2020/9/28上午11:16
 */
@SpringBootApplication
@EnableDiscoveryClient
public class PaymentApplication {

  public static void main(String[] args) {
    SpringApplication.run(PaymentApplication.class,args);
  }
}
