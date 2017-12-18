package com.jaso.system.controller;

import com.jaso.admin.bean.Admin;
import com.jaso.system.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by dllo on 17/12/8.
 */
@Controller
public class SystemController {

    @Autowired
    @Qualifier("systemService")
    private SystemService systemService;

    /**
     * 绑定状态
     */
    @ResponseBody
    @RequestMapping(value = "/bindState")
    public int bindState(Integer id){
        int i = systemService.bindState(id);
        return i;

    }
    /**
     * 绑定账号
     */
    @ResponseBody
    @RequestMapping(value = "/bind")
    public int bind(Admin admin){
        systemService.bind(admin);

        return 1;
    }

    /**
     * 个人信息
     */
    @ResponseBody
    @RequestMapping(value = "ediAdmin")
    public int ediAdmin(Admin admin){
        systemService.ediAdmin(admin);
        return 1;}

    /**
     * 修改密码
     */
    @ResponseBody
    @RequestMapping(value = "modifyPWD")
    public int modify(Admin admin){
        systemService.modifyPWD(admin);
        return 1;}

    /**
     * 上传图片
     */
    @RequestMapping(value = "upPic")
    public String upPic(){
        System.out.println("up");
        return "";
    }

}
