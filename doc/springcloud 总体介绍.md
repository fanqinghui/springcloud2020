# 1. 注册中心
|  注册中心类型   | cap |  优缺点|
|  ----  | ----  | ----  |
| eureka（停更） | cp |保证可用性|
| zookeeper  | ap |保证一致性，临时序列号节点|
| consul  | ap |保证一致性，有consul server端，ui英文不友好|
| nacos  | ap，cp|可以保证可用性，又可以选择保证一致性，还可以做注册中心，ui中文最优选择|

# 2. 服务远程调用
1. ribbon（停更）：是一个类库，集成在项目中，是本地负载均衡，client端获取到了注册中心的服务信息表后换成到JVM本地，自己维护负载均衡的去访问远程服务
负载均衡策略
    1. roundRobinRule：轮询--默认
    2. RandomRule：随机
    3. RetryRule: 重试：先轮询-失败再重试
    4. BestAvailableRule: 选择并发了最小的服务，会过滤掉处于跳闸状态的服务实例
    5. AvaliabilityFilteringRule：先过滤掉故障实例，在选择并发较小的实例，
    6. ZoneAvoidanceRule：默认规则，复合判断server所在区域的性能与可用性来选择服务器
    
2. Feign(openFeign停更) :声明式的WebService客户端，让编写web服务非常容易，只需要再一个接口上添加注册即可
解决方案：
Ribbon+RestTemplate时，利用restTemplate请求的封装处理，形成了可以负载均衡的模板化的调用方法。
Feign是在这个模板方法的基础上进一步做了封装，由feign来帮我们定义与实现依赖服务的定义。只需要自定义一个接口，
使用@Feign进行修饰，就能完成对服务提供方的绑定。
#####feign默认的超时时间是1秒
feign的日志级别
1. NONE，默认，不显示任何日志
2. BASIC，金鸡路请求方法，URL，响应状态码与执行时间
3. HEADERS：除了BASIC中定义的信息之外，还有请求和响应的头信息；
4. FULL：除了HEADERS中信息外，还有请求和响应的正文及原数据；

# 3. 服务降级
1. Hystrix（停更）：（豪猪），分布式系统的延迟和容错的开源库，豪猪保证一个依赖出现问题，不会导致服务整体失败，提高分布式系统的弹性。
断路器，本身是一种开关装置，当某个服务发生故障了，通过断路器的故障监控，向调用方返回一个
符合预期的，可处理备选响应（FallBack），而不是长时间等等或者抛出调用方无法处理的异常，这样就保证了服务
调用方的线程不会被长时间，不必要的占用，避免了故障在分布式系统中的蔓延，乃至雪崩。
2. resilience4j: 用的不多
3. sentinel：（）
#### 几个重要概念
- 服务降级：fallback：服务提供方不可用（程序异常，执行超时，服务熔断触发降级，线程池/信号量打满）了，要给调用方一个有好的提示，例如座机客服电话，给对方提示等待等。
- 服务熔断：rollback：保险丝，达到最大服务最大访问后，直接拒绝访问，拉闸限电,**缺省是5秒内20次调用失败**，就会启动熔断机制（服务先降级-->进而熔断-->恢复调用链路）
- 服务限流：flow：秒杀高并发等操作，研究一窝蜂的过来拥挤，大家排队，一秒钟过N个请求，有序的进行服务请求。
- 服务降级用法：
```
在方法上增加以下标注：
    @HystrixCommand(fallbackMethod = "queryById_errorHandler",commandProperties = {
        @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "3000")
    })
```
- 服务熔断方法
默认条件:默认10秒内超过20个请求次数，超过50%请求失败，就会触发断路器，开启熔断，这时候所有请求都不会再进行转发，调用fallBackMethod降级逻辑。
一定时间窗口期后（默认5秒），熔断器会处于半开状态，会让请求进行转发，如果成功，断路器关闭，若失败，继续熔断（不断重复）
```
  @HystrixCommand(fallbackMethod = "paymentCircuitBreaker_fallBack",commandProperties = {
        @HystrixProperty(name = "circuitBreaker.enabled",value="true"),//是否开启断路器
        @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value = "10"),//请求次数--重要参数
        @HystrixProperty(name=  "circuitBreaker.sleepWindowInMilliseconds",value = "10000"),//默认20秒，自定义设置10秒时间窗口期--重要参数
        @HystrixProperty(name=  "circuitBreaker.errorThresholdPercentage",value = "60")//，默认值50失败率达到多少后跳闸----重要参数
    })
```

# 4. 网关
作用：反向代理，鉴权，熔断，日志监控等等
1. zuul：旧的，是阻塞架构，性能相比来说比较差，
2. Gateway: 新一代网关（web flex netty）：是异步非阻塞架构，动态路由，predicate（断言）和Filter（过滤器），熔断等功能
- Route路由：路由是构建网关的基本模块，它由ID，目标URL，一系列的断言和过滤器组成，如果断言为true，则匹配该路由
- Predicate断言：开发人员可以匹配HTTP请求中的所有内容（请求头或者请求参数），如果请求与断言相匹配则进行路由
- Filter过滤：指的是Spring框架中的GatewayFilter实例，使用过滤器可以在请求路由钱或者路由之后对请求进行修改。

**注意**：gateway是flux netty架构体系，与spring5体系不一样，所以不需要在pom文件里引入springcloud-web，直接引入gateway就可以了
网关配置信息
```
  cloud:   #配置路由信息--匹配的工程是cloud-provider-hystrix-payment8001
    gateway:
      routes:
        - id: payment_routh      # 路由的ID，没有固定规则但要求唯一
          uri: http://localhost:8001            #陪陪后提供服务的路由地址
          predicates:
            - Path=/payment/hystrix/ok/get/**       # 断言，路径相匹配的路由地址
        - id: payment_routh2      # 路由的ID，没有固定规则但要求唯一
          uri: http://localhost:8001            #陪陪后提供服务的路由地址
          predicates:  # 这个是断言---可以有很多，例如path cookie method等
            - Path=/payment/hystrix/circuitbreaker/get/**       # 断言，路径相匹配的路由地址
# 这样以后
8001 payment服务就可以通过9525端口进行路由访问
例如如下：
网关之前端口是8001：http://localhost:8001/payment/hystrix/circuitbreaker/get/1
网关之后端口是9525：http://localhost:9527/payment/hystrix/circuitbreaker/get/1
```

过滤器：spring cloud gateway的声明周期有2种1 pre 2 post
种类有2种：1. gatewayFilter（）   2. GlobalFilter（10多个）
 filters:
   -AddRequestParameter=X-Request-Id,1024 #过滤工厂会在匹配的请求上添加一对请求头，名称为X=Request-Id，值为1024
   
## 3自定义过滤器（）
 - implement GloabFilter，Ordered 实现filter与order两个方法
 - 
 
 ## 配置中心
 1. config
 2. nacos
 
 
 config动态刷新配置：
 1. pom里需要有
 ```
<dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-actuator</artifactId>
    </dependency>
```
2. yml文件增加配置
```
#暴露监控断点
management:
  endpoints:
    web:
      exposure:
        include: "*"
```
3. 配置类增加 @RefreshScope 注解
 问题: 更改配置无法做到动态刷新，需要手动更新，无法做到大范围的广播式的更新： 
```
 curl -X POST "http://localhost:6677/actuator/refresh"
```
需要引入消息总线来解决

 #消息总线 Bus
 微服务架构中，通常会使用轻量级的消息代理来构建一个公用的消息主体，让所有系统中的微服务实例都连接上来，
 这样该主题产生的消息会被所有实例监听和消费，所以称它为消息总线。
 将分布式系统的节点与轻量级消息系统链接起来的框架，整合了java的时间处理机制与消息中间件的功能
 spring cloud bus支持RabbitMq与Kafka，spring cloud alibaba还支持rocketMq
 #### 基本原理
 ConfigClient实例都监听Mq中同一个tipic（topic名字：springcloudBus），当一个服务刷新数据的时候，他会将这个消息放到Topic中，
 这样其它监听统一Topic的服务就能得到通知，然后去更新自身的配置。
 #### 配置中心与消息总线实现方案
 1. 用总线去通知每个微服务实例。。==微服务太多的情况下比较难办，而且让微服务承担了业务以外的功能
 2. （优势方案）让configServer（6666）去做通知服务，只通知（curl）configServer，让configServer去通知configClient
 #### 消息总线配置-例如rabbitMq
 1. server与config的pom.xml引入amqp
 ```
<!--cloud bus 消息总线端rabbitMq-->
<dependency>
  <groupId>org.springframework.cloud</groupId>
  <artifactId>spring-cloud-starter-bus-amqp</artifactId>
</dependency>
```
2. yml中增加mq连接配置与监控点信息
```
  #增加rabbitMq
spring:
  rabbitmq:
    host: localhost
    port: 5672
    username: guest
    password: guest

# rabbitMq 相关配置，暴露bus刷新配置的断点
management:
  endpoints:
    web:
      exposure:
        include: 'bus-refresh'
```
3. 配置中心curl,这样所有config client都可以实时刷新配置
```
curl -X POST "http://locclhost:6666/actuator/bus-refresh"
```

## 配置中心与服务总线如何做到定点通知(curl bus-refresh/{destination})
destination：是微服务名字:具体端口号,例如cloud-config-client:6688
``
curl -X POST "http://localhost:6666/actuator/bus-refresh/cloud-config-client:6688"
``
#cloud stream 消息驱动
stream的目的是屏蔽底层消息中间件的差异，降低切换成本，统一消息的编程模型
目前只支持 rabbitMq与Kafka
stream默认遵循发布订阅形式进行消息通信，rabbitMq使用exchange，kafka使用topic


yml文件里的group属性非常重要：支持持久化与避免消息幂等重复消费

# cloud Sleuth 分布式请求,链路追踪
如果微服务直接调用的过多，一个请求调用了多个服务，如果请求遇到问题，就需要一套链路追踪解决方案
zipkin负责进行展示,zipkin是个jar，可以执行java -jar zipkin-server.jar 在控制台用
localhost:9411在控制台里打开控制台查看链路

