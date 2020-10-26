package com.fqh.cloud.config.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author fqh
 * @Description: 远程配置文件读取
 * @date 2020/10/22下午7:51
 */
//config 增加@RefreshScope,可以达到实时刷新配置信息的目的
@RefreshScope
@RestController
public class ConfigClientController {

  @Value("${config.info}")
  private String configInfo;

  @GetMapping("configInfo")
  public String getConfigInfo(){
    return   configInfo;
  }
}
