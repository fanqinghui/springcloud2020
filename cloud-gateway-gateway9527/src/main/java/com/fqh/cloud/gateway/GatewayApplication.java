package com.fqh.cloud.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author fqh
 * @Description:
 * @date 2020/9/28下午4:01
 */
@SpringBootApplication
@EnableDiscoveryClient
public class GatewayApplication {

  public static void main(String[] args) {
    SpringApplication.run(GatewayApplication.class,args);
  }
}
