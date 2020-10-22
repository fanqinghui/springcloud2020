package com.fqh.cloud.gateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder.Builder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author fqh
 * @Description: 路由信息配置硬编码方式，另外一种方式通过yml配置方式实现
 * @date 2020/10/21下午5:29
 */
@Configuration
public class GatewayConfig {
  @Bean
  public RouteLocator customRouteLocator(RouteLocatorBuilder routeLocatorBuilder){
    Builder routes = routeLocatorBuilder.routes();
     routes
        .route("123",predicateSpec -> predicateSpec.path("/123").uri("https://www.hao123.com"))
        .route("baidu",predicateSpec -> predicateSpec.path("/bd").uri("http://news.baidu.com/guonei"))
         .route("toutiao",r->r.path("/toutiao").uri("https://www.toutiao.com/"))
        .route("jingdong",predicateSpec -> predicateSpec.path("/jd1").uri("https://www.jd.com"))
        .build();
     return routes.build();
  }
}
