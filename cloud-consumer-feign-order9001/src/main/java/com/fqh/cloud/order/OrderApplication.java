package com.fqh.cloud.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author fqh
 * @Description:
 * @date 2020/9/28下午4:01
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class OrderApplication {

  public static void main(String[] args) {
    SpringApplication.run(OrderApplication.class,args);
  }
}
