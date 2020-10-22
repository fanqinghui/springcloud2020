package com.fqh.cloud.payment.service.impl;

import cn.hutool.core.util.IdUtil;
import com.fqh.cloud.entity.payment.Payment;
import com.fqh.cloud.payment.dao.PaymentDao;
import com.fqh.cloud.payment.service.PaymentService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.netflix.ribbon.proxy.annotation.Hystrix;
import java.util.concurrent.TimeUnit;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Payment)表服务实现类
 *
 * @author makejava
 * @since 2020-09-28 13:49:36
 */
@Service("paymentService")
public class PaymentServiceImpl implements PaymentService {
    @Resource
    private PaymentDao paymentDao;

    /**
     * 正常访问--返回ok
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public String queryById_ok(Long id) {

        return "线程池"+Thread.currentThread().getName()+" queryById_ok,ID:"+id;
    }

    /**
     * 访问会出错的实例演示
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    @HystrixCommand(fallbackMethod = "queryById_errorHandler",commandProperties = {
        @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "3000")
    })
    public String queryById_error(Long id) {
        int sleepSecondNum=700;//设置了hystrx熔断超时为3秒，超过3秒就返回熔断处理方法的逻辑
       // int i=1/0;
        try {
            TimeUnit.MILLISECONDS.sleep(sleepSecondNum);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "线程池"+Thread.currentThread().getName()+" queryById_error-成功,ID:"+id;
    }

    /**
     * 服务降级处理-handler
     * @param id
     * @return
     */
    public String queryById_errorHandler(Long id) {
        return "服务降级——线程池"+Thread.currentThread().getName()+" queryById_error-降级_handler,哭脸，请稍候再试:"+id;
    }
    //========服务熔断==========
    //参数详见：HystrixCommandProperties类,里面有HystrixProperty的各种配置信息
    @HystrixCommand(fallbackMethod = "paymentCircuitBreaker_fallBack",commandProperties = {
        @HystrixProperty(name = "circuitBreaker.enabled",value="true"),//是否开启断路器
        @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value = "10"),//请求次数
        @HystrixProperty(name=  "circuitBreaker.sleepWindowInMilliseconds",value = "10000"),//时间窗口期
        @HystrixProperty(name=  "circuitBreaker.errorThresholdPercentage",value = "60")//失败率达到多少后跳闸
    })
    @Override
    public String paymentCircuitBreaker(Long id){
        if(id<0){
            throw new RuntimeException("id不能为负数");
        }
        String serialNumber = IdUtil.simpleUUID();

        return "线程池"+Thread.currentThread().getName()+" paymentCircuitBreaker-成功,流水号:"+serialNumber;
    }

    public String paymentCircuitBreaker_fallBack(Long id){
        return "程序出错了，请稍后再试,id:"+id;
    }
}