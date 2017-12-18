package com.jaso.message.controller;

import com.jaso.base.util.AjaxResult;
import com.jaso.message.bean.Message;
import com.jaso.message.service.ConsumerService;
import com.jaso.message.service.MessageService;
import com.jaso.message.service.ProducerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.TextMessage;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by dllo on 17/12/14.
 */
@Controller
public class MessageController {

    @Resource(name = "queueDestination")
    private Destination queueDestination;

    @Resource
    private ProducerService producerService;

    @Resource
    private ConsumerService consumerService;

    @Resource
    MessageService messageService;
    private AjaxResult ajaxResult;

    /* 页面跳转 */
    @RequestMapping("message-complain")
    public String complainPage(){
        return "message/message-complain";
    }
    @RequestMapping("message-repairs")
    public String repairsPage(){
        return "message/message-repairs";
    }
    @RequestMapping("message-notice")
    public String noticePage(){
        return "message/message-notice";
    }
    @RequestMapping("message-add")
    public String addPage(){
        return "message/message-add";
    }
    @RequestMapping("message-show")
    public String showPage(){
        return "message/message-show";
    }

    @ResponseBody
    @RequestMapping("/consumer")
    public AjaxResult consumerPage() {
        ajaxResult = new AjaxResult<Message>();

        TextMessage textMessage = consumerService.receiveMessage(queueDestination);

        if (null != textMessage) {
            try {
                ajaxResult.setMessage(textMessage.getText());
                Message message = messageService.findByContent(textMessage.getText());
                ajaxResult.setResultData(message);
            } catch (JMSException e) {
                e.printStackTrace();
            }

        }else {
            ajaxResult.setMessage("没有新消息");
        }
        return ajaxResult;
    }

    @ResponseBody
    @RequestMapping("/sendMessage")
    public String sendMsg(Message message) {

        message.setCreate_time(getDate());
        System.out.println(message);
        // 添加数据库
        messageService.addMessage(message);

        producerService.sendMessage(queueDestination, message.getContent());

        return "";
    }

    /**
     * 获取当前时间
     *
     * @return
     */
    private Date getDate() {
        // 创建时间
        Date date = new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        df.format(date);
        return date;
    }
}
