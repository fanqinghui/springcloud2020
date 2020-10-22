package com.fqh.cloud.order.service.impl;

import com.fqh.cloud.order.service.PaymentFeignService;
import org.springframework.stereotype.Service;

/**
 * @author fqh
 * @Description: feign调用的payment service服务降级统一类
 * @date 2020/10/20下午4:35
 */
@Service
public class PaymentFeignServiceImpl implements PaymentFeignService {

  /**
   * 通过主键查询单条数据
   *
   * @param id 主键
   * @return 单条数据
   */
  @Override
  public String selectOneOK(Long id) {
    return "====调用方：paymentFeignService [selectOneOK]****fall back,id:,"+id;
  }

  /**
   * 通过主键查询单条数据
   *
   * @param id 主键
   * @return 单条数据
   */
  @Override
  public String selectOneError(Long id) {
    return "====调用方：paymentFeignService [selectOneError] fall back,id:,"+id;
  }
}
