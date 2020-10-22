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

  /**
   * loadBalanced注解，默认需要开启，如果用了自定义的MyLoadBalancer，需要把注解屏蔽掉
   * @return
   */
  @Bean
  @LoadBalanced
  public RestTemplate getRestTemplate() {
    return new RestTemplate();
  }
}
