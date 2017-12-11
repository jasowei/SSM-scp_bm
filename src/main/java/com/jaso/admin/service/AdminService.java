package com.jaso.admin.service;

import com.jaso.admin.bean.Admin;
import com.jaso.base.bean.IP;

import java.util.List;

/**
 * Created by dllo on 17/12/5.
 */
public interface AdminService {


    Admin select_adminById(int admin_id);

    Admin select_adminByLoginName(String login_name);

    Admin select_adminByLoginNameAndPwd(Admin admin);

    /**
     * 添加 IP 对象
     */
    void insertIP(IP ip);

    /**
     * 根据 IP地址查询
     */
    IP select_IPByName(String ip_name);

    /**
     * 跟新 IP 登录时间和 登录次数
     * @param ip
     */
    void updata_IPLoginAc(IP ip);

    /**
     * 查询所有的用户
     */
    List<Admin> select_allAdmin();

    List<Admin> select_adminByRoleId(int role_id);
}
