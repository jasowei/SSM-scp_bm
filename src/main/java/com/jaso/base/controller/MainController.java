package com.jaso.base.controller;

import com.jaso.admin.bean.Admin;
import com.jaso.base.util.VerifyCode;
import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Created by dllo on 17/12/4.
 */

@Controller
public class MainController {

    /**
     * 登录页
     */
    @RequestMapping(value = "/login")
    public String login(){
        return "login";
    }

    /**
     * 主页
     */
    @RequestMapping(value = "/index")
    public String index(){
        return "index";
    }

    /**
     * 欢迎
     */
    @RequestMapping(value = "welcome")
    public String welcome(){
        return "welcome";
    }


//----------admin------------
    @RequestMapping(value = "admin-role")
    public String adminRole(){
        return "admin/admin-role";
    }

    @RequestMapping(value = "admin-role-add")
    public String adminRoleAdd(){
        return "admin/admin-role-add";
    }

    @RequestMapping(value = "admin-list")
    public String adminList(){return "admin/admin-list";}

    @RequestMapping(value = "admin-add")
    public String adminAdd(){
        return "admin/admin-add";
    }

    @RequestMapping(value = "menu")
    public String menu(){
        return "admin/menu";
    }

    @RequestMapping(value = "menu-add")
    public String menuAdd(){
        return "admin/menu-add";
    }


}
