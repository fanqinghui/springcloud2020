package com.fqh.cloud.payment.service.impl;

import com.fqh.cloud.entity.payment.Payment;
import com.fqh.cloud.payment.dao.PaymentDao;
import com.fqh.cloud.payment.service.PaymentService;
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
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Payment queryById(Long id) {
        return this.paymentDao.selectByPrimaryKey(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<Payment> queryAllByLimit(int offset, int limit) {
        //return this.paymentDao.queryList(offset, limit);
        return null;
    }

    /**
     * 新增数据
     *
     * @param payment 实例对象
     * @return 实例对象
     */
    @Override
    public Payment insert(Payment payment) {
        this.paymentDao.insert(payment);
        return payment;
    }

    /**
     * 修改数据
     *
     * @param payment 实例对象
     * @return 实例对象
     */
    @Override
    public Payment update(Payment payment) {
        this.paymentDao.updateByPrimaryKeySelective(payment);
        return this.queryById(payment.getId());
    }

    /**
     * 通过主键删除数据
     *  ok
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.paymentDao.deleteByPrimaryKey(id) > 0;
    }
}