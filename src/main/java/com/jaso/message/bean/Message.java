package com.jaso.message.bean;

import java.util.Date;

/**
 * 信息
 */
public class Message {

    private int message_id;//id
    private String message_title;//标题
    private String content;//内容
    private int admin_id;//发起人id
    private int type;//消息类型: 0代表公告; 1代表投诉; 2代表报修
    private Date create_time;//创建时间

    public Message() {
    }

    public Message(String message_title, String content, int admin_id, int type, Date create_time) {
        this.message_title = message_title;
        this.content = content;
        this.admin_id = admin_id;
        this.type = type;
        this.create_time = create_time;
    }

    public Message(int message_id, String message_title, String content, int admin_id, int type, Date create_time) {
        this.message_id = message_id;
        this.message_title = message_title;
        this.content = content;
        this.admin_id = admin_id;
        this.type = type;
        this.create_time = create_time;
    }

    @Override
    public String toString() {
        return "Message{" +
                "message_id=" + message_id +
                ", message_title='" + message_title + '\'' +
                ", content='" + content + '\'' +
                ", admin_id=" + admin_id +
                ", type=" + type +
                ", create_time=" + create_time +
                '}';
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getMessage_id() {
        return message_id;
    }

    public void setMessage_id(int message_id) {
        this.message_id = message_id;
    }

    public String getMessage_title() {
        return message_title;
    }

    public void setMessage_title(String message_title) {
        this.message_title = message_title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getAdmin_id() {
        return admin_id;
    }

    public void setAdmin_id(int admin_id) {
        this.admin_id = admin_id;
    }
}
