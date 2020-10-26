package com.fqh.stream.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.messaging.Message;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author fqh
 * @Description:
 * @date 2020/10/23下午3:41
 */
@RestController
@EnableBinding(Sink.class)
public class ReceiveMessageController {

  @Value("${server.port}")
  private String serverPort;

  @StreamListener(Sink.INPUT)
  public void receiveMessage(Message<String> message){
    System.out.println("消费者端口："+serverPort+" 接收到的消费内容："+message.getPayload());
  }
}
