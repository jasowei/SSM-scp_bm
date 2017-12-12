package com.jaso.admin.controller;

import com.jaso.admin.bean.Admin;
import com.jaso.admin.service.AdminService;
import com.jaso.base.util.AjaxResult;
import com.jaso.base.util.VerifyCode;
import com.sun.deploy.net.HttpRequest;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Date;

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
    private AjaxResult<Admin> ajaxResult;

    @RequestMapping(value = "/getVerifyCode")
    public void getVerifyCode(HttpServletRequest request,
                              HttpServletResponse response) throws IOException {
        VerifyCode verifyCode = new VerifyCode();
        BufferedImage image = verifyCode.getImage();
        verifyCodeText = verifyCode.getText().toLowerCase();
        verifyCode.output(image, response.getOutputStream());
    }

    /**
     * 登录
     * @return
     */
    @ResponseBody
    @RequestMapping( value = "/loginAdmin")
    public AjaxResult login(Admin admin, String code, HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println(admin);
        System.out.println(code);
        System.out.println(verifyCodeText);

        ajaxResult = new AjaxResult<Admin>();

        // 如果在shirospring的配置文件中, 配置了表单认证过滤器
        // 那么在这个方法中只需要处理异常信息即可

        String exClassName =
                (String) request.getAttribute("shiroLoginFailure");

        if (UnknownAccountException.class.getName().equals(exClassName)){
            ajaxResult.setStatus(false);
            ajaxResult.setMessage("账号不存在");
        } else if (IncorrectCredentialsException.class.getName().equals(exClassName)){
            ajaxResult.setStatus(false);
            ajaxResult.setMessage("密码错误");
        }else if (!code.equalsIgnoreCase(verifyCodeText)){
            ajaxResult.setStatus(false);
            ajaxResult.setMessage("验证码不正确");
        }

        return ajaxResult;
    }

    @ResponseBody
    @RequestMapping("/showAdmin")
    public AjaxResult showAdmin(Admin admin){
        ajaxResult = new AjaxResult<Admin>();

        Admin admin1 = adminService.select_adminByLoginNameAndPwd(admin);

        System.out.println(admin1);
        ajaxResult.setResultData(admin1);

        return ajaxResult;

    }

}
