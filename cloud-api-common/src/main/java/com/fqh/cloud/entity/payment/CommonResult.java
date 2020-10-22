package com.fqh.cloud.entity.payment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author fqh
 * @Description: 通用返回参数
 * @date 2020/9/28下午1:14
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommonResult<T> {
  private Integer code;
  private String message;
  private T data;

  public CommonResult(Integer code,String message){
    this(code,message,null);
  }
}
