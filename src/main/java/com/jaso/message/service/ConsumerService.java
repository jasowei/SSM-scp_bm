package com.jaso.message.service;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.TextMessage;

/**
 * Created by dllo on 17/11/29.
 */
@Service
public class ConsumerService {

    @Resource(name = "jmsTemplate")
    private JmsTemplate jmsTemplate;

    public TextMessage receiveMessage(Destination destination) {

        //如果destination不变, 可以这样获取
//        jmsTemplate.getDefaultDestinationName();
//        jmsTemplate.getDefaultDestination();

        TextMessage textMessage = (TextMessage) jmsTemplate.receive(destination);

        if (textMessage != null) {
            try {
                System.out.println("接收到的消息内容是: " + textMessage.getText());
            } catch (JMSException e) {
                e.printStackTrace();
            }
        }

        return textMessage;
    }


}
