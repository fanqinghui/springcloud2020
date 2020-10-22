package com.fqh.cloud.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author fqh
 * @Description:
 * @date 2020/9/28下午7:24
 */
@SpringBootApplication
@EnableEurekaServer
public class EurekaApplication {

  public static void main(String[] args) {
    SpringApplication.run(EurekaApplication.class,args);
  }
}
