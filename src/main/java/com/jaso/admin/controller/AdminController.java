package com.jaso.admin.controller;

import com.github.pagehelper.PageInfo;
import com.jaso.admin.bean.Admin;
import com.jaso.admin.bean.Permit;
import com.jaso.admin.bean.Role;
import com.jaso.admin.mapper.AdminMapper;
import com.jaso.admin.service.AdminService;
import com.jaso.admin.service.RoleService;
import com.jaso.base.bean.IP;
import com.jaso.base.util.AjaxResult;
import com.jaso.base.util.VerifyCode;
import com.lanou.mail.Mail;
import com.lanou.mail.MailUtils;
import com.sun.deploy.net.HttpRequest;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by dllo on 17/12/5.
 */
@Controller
public class AdminController {

    @Autowired
    @Qualifier("adminService")
    private AdminService adminService;

    @Resource
    private RoleService roleService;

    private String verifyCodeText;
    private AjaxResult ajaxResult;


    /**
     * 获取验证码
     */
    @RequestMapping(value = "/getVerifyCode")
    public void getVerifyCode(HttpServletRequest request,
                              HttpServletResponse response) throws IOException {
        VerifyCode verifyCode = new VerifyCode();
        BufferedImage image = verifyCode.getImage();
        verifyCodeText = verifyCode.getText().toLowerCase();
        verifyCode.output(image, response.getOutputStream());
    }

    /**
     * --> 登录
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/loginAdmin")
    public AjaxResult login(String code, HttpServletRequest request) throws Exception {

        ajaxResult = new AjaxResult<Admin>();

        // 如果在shirospring的配置文件中, 配置了表单认证过滤器
        // 那么在这个方法中只需要处理异常信息即可

        String exClassName =
                (String) request.getAttribute("shiroLoginFailure");

        if (UnknownAccountException.class.getName().equals(exClassName)) {
            ajaxResult.setStatus(false);
            ajaxResult.setMessage("账号不存在");
        } else if (IncorrectCredentialsException.class.getName().equals(exClassName)) {
            ajaxResult.setStatus(false);
            ajaxResult.setMessage("密码错误");
        } else if (!code.equalsIgnoreCase(verifyCodeText)) {
            ajaxResult.setStatus(false);
            ajaxResult.setMessage("验证码不正确");
        }

        return ajaxResult;
    }

    /**
     * 获取登录用户信息
     */
    @ResponseBody
    @RequestMapping("/showAdmin")
    public AjaxResult showAdmin(Admin admin) {
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

    /**
     * 获取登录IP
     */
    @ResponseBody
    @RequestMapping("/getIP")
    public AjaxResult getIpAddr(HttpServletRequest request) {
        ajaxResult = new AjaxResult<String>();

        String ipAddress = request.getHeader("x-forwarded-for");

        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("Proxy-Client-IP");
        }
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getRemoteAddr();
            if (ipAddress.equals("127.0.0.1") || ipAddress.equals("0:0:0:0:0:0:0:1")) {
                //根据网卡取本机配置的IP
                InetAddress inet = null;
                try {
                    inet = InetAddress.getLocalHost();
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                }
                ipAddress = inet.getHostAddress();
            }
        }
        //对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
        if (ipAddress != null && ipAddress.length() > 15) { //"***.***.***.***".length() = 15
            if (ipAddress.indexOf(",") > 0) {
                ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
            }
        }

        // 数据库查询
        IP ip = adminService.select_IPByName(ipAddress);
        if (null == ip) {//不存在添加

            // 创建IP新的IP对象, 并存入数据库
            ip = new IP(ipAddress, getDate(), 1);
            adminService.insertIP(ip);
            ajaxResult.setStatus(false);
        } else {//存在,跟新
            ajaxResult.setResultData(ip);
            ip.setLogin_account(ip.getLogin_account() + 1);
            ip.setLogin_date(getDate());
            adminService.updata_IPLoginAc(ip);
        }
        return ajaxResult;
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

    /**
     * 查询所有的用户
     */
    @ResponseBody
    @RequestMapping("/showAdminList")
    public PageInfo<Admin> showAdminList(Integer pageNum, Integer pageSize) {
        PageInfo<Admin> admins = adminService.select_allAdmin(pageNum, pageSize);
        return admins;
    }

    /**
     * 查询权限
     */
    @ResponseBody
    @RequestMapping("/showRoles")
    public PageInfo<Role> showRoles(Integer pageNum, Integer pageSize) {
        PageInfo<Role> roles = roleService.select_allRoles(pageNum, pageSize);
        return roles;
    }

    /**
     * 根据角色id查询角色拥有者
     */
    @ResponseBody
    @RequestMapping("/getAdminByRoleId")
    public AjaxResult getAdminByRoleId(int role_id) {
        ajaxResult = new AjaxResult<List<Admin>>();
        // 调用查询方法
        List<Admin> admins = adminService.select_adminByRoleId(role_id);
        // 判空
        if (null == admins) {
            ajaxResult.setStatus(false);
            ajaxResult.setMessage("该角色还没有人有! ");
        }
        ajaxResult.setResultData(admins);

        return ajaxResult;
    }

    /**
     * 获取角色总记录数
     */
    @ResponseBody
    @RequestMapping("/getRoleAllAccount")
    public int getRoleAllAccount() {
        int account = roleService.getAllAccount();
        return account;
    }

    /**
     * 查询所有权限
     */
    @ResponseBody
    @RequestMapping("/showAllPermit")
    public AjaxResult showAllPermit() {
        ajaxResult = new AjaxResult<Permit>();

        List<Permit> permitList = roleService.select_allPermit();
        ajaxResult.setResultData(permitList);

        return ajaxResult;
    }

    /**
     * 角色表单回填
     */
    @ResponseBody
    @RequestMapping("/getRole")
    public AjaxResult getRole(int role_id) {
        ajaxResult = new AjaxResult<Role>();

        System.out.println(role_id);

        Role role = roleService.select_RoleById(role_id);
        System.out.println(role);

        if (role == null) {
            ajaxResult.setStatus(false);
            ajaxResult.setMessage("没有该角色");
        }
        ajaxResult.setResultData(role);

        return ajaxResult;
    }

    /**
     * 检查角色名
     */
    @ResponseBody
    @RequestMapping("/checkRoleName")
    public AjaxResult checkRoleName(String role_name, int role_id) {
        ajaxResult = new AjaxResult<Role>();
        // id为0则是添加界面的验证 , id 不为 0 , 编辑界面的验证
        Role role = roleService.select_RoleByNameNoPermit(role_name);
        if ((null != role && role_id == 0) || (role != null && role.getRole_id() != role_id)) {
            ajaxResult.setStatus(false);
            ajaxResult.setMessage("该角色名已被占用");
        }
        ajaxResult.setResultData(role);
        return ajaxResult;
    }

    /**
     * 编辑角色
     */
    @ResponseBody
    @RequestMapping("/roleEdit")
    public AjaxResult roleEdit(Role role, String permit) {
        ajaxResult = new AjaxResult<Role>();
        // 调用修改业务
        roleService.updateRole(role, permit);

        return ajaxResult;
    }

    /**
     * 添加
     */
    @ResponseBody
    @RequestMapping("/roleAdd")
    public AjaxResult roleAdd(Role role, String permit) {
        ajaxResult = new AjaxResult<Role>();
        System.out.println(role);
        System.out.println(permit);
        Role role1 = roleService.select_RoleByNameNoPermit(role.getRole_name());
        if (role1 == null) {
            roleService.addRole(role, permit);
        } else {
            ajaxResult.setMessage("添加失败, 给角色名已被占用");
            ajaxResult.setStatus(false);
        }
        return ajaxResult;
    }

    /**
     * 删除角色
     */
    @ResponseBody
    @RequestMapping("/deleteRole")
    public AjaxResult deleteRole(int role_id) {
        ajaxResult = new AjaxResult();
        // 不能删除超级管理员
        if (role_id == 1) {
            ajaxResult.setMessage("没有权限删除该角色");
            ajaxResult.setStatus(false);
            return ajaxResult;
        }
        roleService.deleteRoleById(role_id);

        return ajaxResult;
    }

    /**
     * 批量删除
     */
    @ResponseBody
    @RequestMapping("/deleteMore")
    public AjaxResult deleteMore(String roleId) {
        ajaxResult = new AjaxResult();
        // 要删除的id串不能为空
        if (null == roleId) {
            ajaxResult.setMessage("至少选择一条数据");
            ajaxResult.setStatus(false);
            return ajaxResult;
        }

        roleService.deleteRoleMore(roleId);

        return ajaxResult;
    }

    /**
     * 获取Admin的总条数
     */
    @ResponseBody
    @RequestMapping("/getAdminAccount")
    public Integer getAdminAccount() {
        return adminService.select_allAdmin().size();
    }

    /**
     *  获取Admin的角色
     */
    @ResponseBody
    @RequestMapping("/getAdminRoles")
    public AjaxResult getAdminRoles(int admin_id){
        ajaxResult = new AjaxResult<Admin>();

        Admin admin = adminService.select_adminById(admin_id);
        ajaxResult.setResultData(admin);

        return ajaxResult;
    }

    /**
     * 高级搜索
     */
    @ResponseBody
    @RequestMapping("/search")
    public AjaxResult search(String keyWord,  HttpServletRequest request){
        ajaxResult = new AjaxResult<Admin>();
        List<Admin> search = adminService.search(keyWord);
        ajaxResult.setResultData(search);

        return ajaxResult;
    }
    /**
     * 查询所有角色
     */
    @ResponseBody
    @RequestMapping("/getRoles")
    public AjaxResult getRoles(){
        ajaxResult = new AjaxResult<Role>();
        List<Role> roleList = roleService.select_allRoles();
        ajaxResult.setResultData(roleList);
        return ajaxResult;
    }

    /**
     * 用户名查重
     */
    @ResponseBody
    @RequestMapping("/checkAdminName")
    public AjaxResult checkAdminName(String adminName){
        ajaxResult = new AjaxResult();
        Admin admin = adminService.select_adminByLoginName(adminName);
        if (null != admin){
            ajaxResult.setStatus(false);
            ajaxResult.setMessage("该用户名已被使用");
        }
        return ajaxResult;
    }

    /**
     * 电话查重
     */
    @ResponseBody
    @RequestMapping("/checkTelephone")
    public AjaxResult checkTelephone(String telephone){
        ajaxResult = new AjaxResult();
        Admin admin = adminService.select_adminByTel(telephone);
        if (null != admin){
            ajaxResult.setStatus(false);
            ajaxResult.setMessage("该电话号码已被使用");
        }
        return ajaxResult;
    }

    /**
     * 用户添加
     */
    @ResponseBody
    @RequestMapping("/addAdmin")
    public AjaxResult addAdmin(Admin admin, int adminRole){
        ajaxResult = new AjaxResult();
        System.out.println(admin);
        System.out.println(adminRole);

        Admin admin1 = adminService.select_adminByLoginName(admin.getLogin_name());
        Admin admin2 = adminService.select_adminByTel(admin.getTelephone());
        Admin admin3 = adminService.findByEmail(admin.getEmail());
        if ((admin1 != null)||(admin2 != null)||(admin2 != null)){
            ajaxResult.setStatus(false);
            ajaxResult.setMessage("用户名或手机号或邮箱已被使用");
            return ajaxResult;
        }

        return ajaxResult;
    }


}
