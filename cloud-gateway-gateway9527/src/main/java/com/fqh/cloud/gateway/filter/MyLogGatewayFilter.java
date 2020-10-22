package com.fqh.cloud.gateway.filter;

import java.time.LocalDateTime;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @author fqh
 * @Description:
 * @date 2020/10/21下午11:39
 */
@Component
@Slf4j
public class MyLogGatewayFilter implements GlobalFilter, Ordered {

  @Override
  public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
    log.info("***MyLogGatewayFilter***"+ LocalDateTime.now());
    String uname = exchange.getRequest().getQueryParams().getFirst("uname");
    if(uname==null){
      log.info("*******用户名为空");
      exchange.getResponse().setStatusCode(HttpStatus.NOT_ACCEPTABLE);
    }
    return chain.filter(exchange);
  }

  @Override
  public int getOrder() {
    return 0;
  }
}