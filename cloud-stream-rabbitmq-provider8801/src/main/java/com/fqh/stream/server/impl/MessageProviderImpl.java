package com.fqh.stream.server.impl;

import cn.hutool.core.lang.UUID;
import com.fqh.stream.server.MessageProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

/**
 * @author fqh
 * @Description:
 * @date 2020/10/23下午3:35
 */
//@Service
@EnableBinding(Source.class)
public class MessageProviderImpl implements MessageProvider {

  @Autowired
  private MessageChannel output;

  @Override
  public String send() {
    String content = UUID.randomUUID().toString();
    System.out.println(content);
    Message<String> msg = MessageBuilder.withPayload(content)
        .setHeader("patitionKey", "what").build();
    output.send(msg);
    return "has send:"+content;
  }
}
