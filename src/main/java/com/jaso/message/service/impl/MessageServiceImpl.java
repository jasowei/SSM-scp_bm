package com.jaso.message.service.impl;

import com.jaso.message.bean.Message;
import com.jaso.message.mapper.MessageMapper;
import com.jaso.message.service.MessageService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by dllo on 17/12/14.
 */
@Service
public class MessageServiceImpl implements MessageService {

    @Resource
    MessageMapper messageMapper;

    @Override
    public void addMessage(Message message) {
        messageMapper.addMessage(message);
    }

    @Override
    public Message findByContent(String text) {
        return messageMapper.findByContent(text);
    }
}
