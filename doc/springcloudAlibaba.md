# nacos
1. 下载安装
官网:https://nacos.io/zh-cn/index.html
2. nacos/bin目录下 启动
```
sh startup.sh -m standalone
```
3. 工程依赖
```
<dependency>
    <groupId>com.alibaba.cloud</groupId>
    <artifactId>spring-cloud-starter-alibaba-nacos-config</artifactId>
    <version>${latest.version}</version>
</dependency>
```
springcloud alibaba与springboot springcloud 版本关系详见一下wiki
https://github.com/alibaba/spring-cloud-alibaba/wiki/%E7%89%88%E6%9C%AC%E8%AF%B4%E6%98%8E

4. yml配置
```
server:
  port: 8001
spring:
  application:
    name: nacos-payment-provider
  cloud:
    nacos:
      discovery:
        server-addr: 127.0.0.1:8848

#暴露监控断点
management:
  endpoints:
    web:
      exposure:
        include: '*'
```