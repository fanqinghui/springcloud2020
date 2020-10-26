package com.fqh.stream.controller;

import com.fqh.stream.server.MessageProvider;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author fqh
 * @Description:
 * @date 2020/10/23下午3:41
 */
@RestController
public class SendMessageController {

  @Autowired
  private MessageProvider messageProvider;

  @GetMapping("sendMessage")
  public String sendMessage(){
    return messageProvider.send();
  }
}
