package com.fqh.cloud.order.loadBalancer;

import java.util.List;
import org.springframework.cloud.client.ServiceInstance;

/**
 * @author fqh
 * @Description:
 * @date 2020/10/19下午2:00
 */
public interface LoadBalancer {

  /**
   * 负载均衡选择器
   * @param serviceInstanceList
   * @return
   */
  public ServiceInstance instances(List<ServiceInstance> serviceInstanceList);
}
