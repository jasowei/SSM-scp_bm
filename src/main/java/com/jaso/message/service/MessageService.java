package com.jaso.message.service;

import com.jaso.message.bean.Message;

/**
 * Created by dllo on 17/12/14.
 */
public interface MessageService {

    /**
     * 添加信息
     */
    void addMessage(Message message);

    /**
     * z根据内容查找
     */
    Message findByContent(String text);
}
