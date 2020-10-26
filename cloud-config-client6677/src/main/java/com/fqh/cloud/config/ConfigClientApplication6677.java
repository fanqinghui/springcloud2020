package com.fqh.cloud.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author fqh
 * @Description:
 * @date 2020/10/22上午11:08
 */
@SpringBootApplication
@EnableDiscoveryClient
public class ConfigClientApplication6677 {

  public static void main(String[] args) {
    SpringApplication.run(ConfigClientApplication6677.class,args);
  }
}
