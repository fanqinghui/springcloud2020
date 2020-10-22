package com.fqh.cloud.order.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author fqh
 * @Description:
 * @date 2020/9/28下午4:09
 */
@Configuration
public class RestTemplateConfig {

  @Bean
  @LoadBalanced
  public RestTemplate getRestTemplate() {
    return new RestTemplate();
  }
}
