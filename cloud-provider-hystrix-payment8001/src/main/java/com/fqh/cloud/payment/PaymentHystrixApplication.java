package com.fqh.cloud.payment;

import com.netflix.hystrix.contrib.metrics.eventstream.HystrixMetricsStreamServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

/**
 * @author fqh
 * @Description:
 * @date 2020/9/28上午11:16
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableCircuitBreaker//服务降级
public class PaymentHystrixApplication {

  public static void main(String[] args) {
    SpringApplication.run(PaymentHystrixApplication.class,args);
  }

  /**
   * 为了服务监控而配置，是springcloud升级以后的一个bug
   * @return
   */
  @Bean
  public ServletRegistrationBean getServlet(){
    HystrixMetricsStreamServlet streamServlet = new HystrixMetricsStreamServlet();
    ServletRegistrationBean registrationBean = new ServletRegistrationBean(streamServlet);
    registrationBean.setLoadOnStartup(1);
    registrationBean.addUrlMappings("/hystrix.stream");
    registrationBean.setName("HystrixMetricsStramServlet");
    return registrationBean;
  }
}
