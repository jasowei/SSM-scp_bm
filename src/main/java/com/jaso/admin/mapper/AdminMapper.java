package com.jaso.admin.mapper;

import com.jaso.admin.bean.Admin;
import com.jaso.base.bean.IP;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by dllo on 17/12/5.
 */
public interface AdminMapper {

    /**
     * 根据id查询
     * @param admin_id
     * @return
     */
    Admin select_adminById(int admin_id);


    Admin select_adminByLoginName(String login_name);


//    List<Admin> select_adminByLoginNameAndPwd(Admin admin);
    Admin select_adminByLoginNameAndPwd(Admin admin);

    /**
     * 添加 IP 对象
     */
    void insert_IP(IP ip);

    /**
     * 根据 IP地址查询
     */
    IP select_IPByName(String ip_name);

    /**
     * 跟新 IP 的登录时间和登录次数
     */
    void update_IPLoginAcc(IP ip);

    /**
     * 查询所有用户
     */
    List<Admin> select_allAdmin();
    List<Admin> select_allAdminNoRole();

    List<Admin> select_adminByRoleId(int role_id);

    void insertAdminToRole(@Param("admin_id") int i, @Param("role_id") int role_id);

    Admin findByEmail(String email);

    void updateByEmail(String email);
}
