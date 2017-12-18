package com.jaso.message.service;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

/**
 * 消息生产者服务类
 */
@Service
public class ProducerService {

    @Resource(name = "jmsTemplate")
    private JmsTemplate jmsTemplate;

    public void sendMessage(Destination destination, String msg){

        System.out.println("发送: " + msg + " 到: " + destination);

        // 消息生成器(内部类)(注释掉的是lambda表达式)
//        MessageCreator messageCreator = session -> session.createTextMessage(msg);
        MessageCreator messageCreator = new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                return session.createTextMessage(msg);
            }
        };

        // 发送消息
        jmsTemplate.send(destination, messageCreator);

    }




}
