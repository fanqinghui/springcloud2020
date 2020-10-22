package com.fqh.cloud.order.loadBalancer;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.stereotype.Component;

/**
 * @author fqh
 * @Description: 自定义负载均衡器
 * @date 2020/10/19下午1:58
 */
@Component
public class MyLoadBalancer implements LoadBalancer{

  AtomicInteger atomicInteger=new AtomicInteger(0);

  /**
   * 负载均衡选择器--如果需要用自定义的负载均衡，需要把RestTemplateConfig 的getRestTemplate方法的@LoadBalance注解注释掉
   */
  @Override
  public ServiceInstance instances(List<ServiceInstance> serviceInstanceList) {
    int nextInex=getAndIncrement()% serviceInstanceList.size();
    return serviceInstanceList.get(nextInex);
  }

  private int getAndIncrement() {
    int current;
    int next;//代表第几次访问
    while (true){
        current=atomicInteger.get();
        next=current>=Integer.MAX_VALUE?0:current+1;
        if(atomicInteger.compareAndSet(current,next)){
          System.out.println("当前是第几次访问:Index:"+next);
          return next;
        }
    }
  }
}
