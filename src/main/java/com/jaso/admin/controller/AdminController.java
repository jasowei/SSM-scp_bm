package com.jaso.admin.controller;

import com.jaso.admin.bean.Admin;
import com.jaso.admin.service.AdminService;
import com.jaso.base.util.VerifyCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Created by dllo on 17/12/5.
 */
@Controller
public class AdminController {

    @Autowired
    @Qualifier("adminService")
    private AdminService adminService;



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

}
