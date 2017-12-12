package com.jaso.admin.controller;

import com.jaso.admin.bean.Admin;
import com.jaso.admin.service.AdminService;
import com.jaso.base.util.AjaxResult;
import com.jaso.base.util.VerifyCode;
import com.lanou.mail.Mail;
import com.lanou.mail.MailUtils;
import com.sun.deploy.net.HttpRequest;

//import javax.mail.MessagingException;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


import javax.mail.MessagingException;
import javax.mail.Session;
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

    private AjaxResult<Admin> ajaxResult;



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

    /**
     * 登录
     * @return
     */
    @ResponseBody
    @RequestMapping( value = "/loginAdmin")
    public AjaxResult login(Admin admin, String code, HttpServletRequest request, HttpServletResponse response) throws IOException {

        ajaxResult = new AjaxResult<Admin>();

        // 如果在shirospring的配置文件中, 配置了表单认证过滤器
        // 那么在这个方法中只需要处理异常信息即可

        String exClassName =
                (String) request.getAttribute("shiroLoginFailure");

        if (UnknownAccountException.class.getName().equals(exClassName)){
            ajaxResult.setStatus(false);
            ajaxResult.setMessage("账号不存在!");
        } else if (IncorrectCredentialsException.class.getName().equals(exClassName)){
            ajaxResult.setStatus(false);
            ajaxResult.setMessage("密码错误!");
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

        ajaxResult.setResultData(admin1);

        return ajaxResult;

    }

    /**
     * 根据id查信息
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "selectAdminById")
    public Admin selectAdminById(Integer id){
        Admin admin = adminService.select_adminById(id);
        return admin;
    }

    /**
     * 忘记密码
     * 验证邮箱
     */
    @ResponseBody
    @RequestMapping("verifyEmail")
    public int verifyEmail(String email){
        System.out.println("email :"+email);
        Admin admin = adminService.findByEmail(email);
        if (admin != null){
            return 1;
        }
        return 0;
    }
    /**
     * 忘记密码
     * 发邮件&初始密码
     */
    @ResponseBody
    @RequestMapping("sendEmail")
    public int sendEmail(String email){
        System.out.println("发送邮件到 :"+email);
        try {
        /*   1>. 得到 session   */
            Session session = MailUtils.createSession("smtp.163.com","xinrugao@163.com", "why1993521");
        /*   2>.创建邮件对象   */
            Mail mail = new Mail("xinrugao@163.com",email,"FROM.Jaso.wei",
                    "<h1>小区业主服务平台-后台管理系统</h1><hr><br>" +
                            "<h2>新的初始密码 : 123456</h2><h4>*为了账户安全,建议您尽快修改初始密码.</h4>");
        /*  3>.发送    */
            MailUtils.send(session,mail);


        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //修改数据库
        adminService.updateByEmail(email);
        System.out.println("send success !");
        return 1;
    }

}
