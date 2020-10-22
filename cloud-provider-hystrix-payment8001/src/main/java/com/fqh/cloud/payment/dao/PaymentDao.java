package com.fqh.cloud.payment.dao;

import com.fqh.cloud.entity.payment.Payment;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author fqh
 * @Description:
 * @date 2020/9/28下午1:17
 */
@Mapper
public interface PaymentDao {
      int deleteByPrimaryKey(Long id);

      int insert(Payment record);

      int insertSelective(Payment record);

     Payment selectByPrimaryKey(Long id);

      int updateByPrimaryKeySelective(Payment record);

      int updateByPrimaryKey(Payment record);
}
