package com.jaso.message.mapper;

import com.jaso.message.bean.Message;

/**
 * Created by dllo on 17/12/14.
 */
public interface MessageMapper {
    /**
     * 添加信息
     */
    void addMessage(Message message);

    Message findByContent(String text);
}
