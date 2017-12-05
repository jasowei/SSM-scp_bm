package com.jaso.base.controller;

import com.jaso.base.util.VerifyCode;
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
    @RequestMapping(value = "login")
    public String login(){
        return "login";
    }

    /**
     * 主页
     */
    @RequestMapping(value = "index")
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

    /**
     * 获取验证码
     */
    private String verifyCodeText;
    @RequestMapping(value = "/getVerifyCode")
    public void getVerifyCode(HttpServletRequest request,
                              HttpServletResponse response) throws IOException {
        VerifyCode verifyCode = new VerifyCode();
        BufferedImage image = verifyCode.getImage();
        verifyCodeText = verifyCode.getText().toLowerCase();
        verifyCode.output(image, response.getOutputStream());
    }
//----------------------
    @RequestMapping(value = "admin-role")
    public String adminrole(){
        return "admin/admin-role";
    }

    @RequestMapping(value = "admin-list")
    public String adminlist(){return "admin/admin-list";}


}
