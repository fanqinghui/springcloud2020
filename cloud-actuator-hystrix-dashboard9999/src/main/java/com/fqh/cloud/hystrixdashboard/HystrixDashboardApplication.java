package com.fqh.cloud.hystrixdashboard;

import com.netflix.hystrix.contrib.metrics.eventstream.HystrixMetricsStreamServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.context.annotation.Bean;

/**
 * @author fqh
 * @Description:
 * @date 2020/9/28下午4:01
 */
@SpringBootApplication
@EnableHystrixDashboard
public class HystrixDashboardApplication {

  public static void main(String[] args) {
    SpringApplication.run(HystrixDashboardApplication.class,args);
  }

}
