package com.fqh.cloud.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author fqh
 * @Description:
 * @date 2020/9/28下午4:01
 */
@SpringBootApplication
//@EnableEurekaClient
@EnableDiscoveryClient
public class OrderApplication9001 {

  public static void main(String[] args) {
    SpringApplication.run(OrderApplication9001.class,args);
  }
}
