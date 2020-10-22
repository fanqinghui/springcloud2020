package com.fqh.cloud.order.config;

import feign.Logger;
import feign.Logger.Level;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author fqh
 * @Description: feign 配置类
 * @date 2020/10/20上午1:14
 */
@Configuration
public class FeignConfig {

  /**
   * 默认使用详细日志-FULL
   * @return
   */
  @Bean
  public Logger.Level feignLoggerLevel(){
    return Level.FULL;
  }
}
