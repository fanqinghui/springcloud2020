package com.fqh.cloud.entity.payment;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author fqh
 * @Description:
 * @date 2020/9/28下午1:11
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Payment implements Serializable {
  private Long id;
  private String serial;
}
