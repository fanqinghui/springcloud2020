package com.fqh.cloud.ribbonrule;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author fqh
 * @Description: 自己设置的ribbon负载均衡规则
 * @date 2020/10/19上午10:20
 */
@Configuration
public class MyRibbonRule {

  @Bean
  public IRule myRule() {
    return new RandomRule();
  }

}
