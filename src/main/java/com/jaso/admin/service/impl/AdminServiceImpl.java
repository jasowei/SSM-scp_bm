package com.jaso.admin.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jaso.admin.bean.Admin;
import com.jaso.admin.bean.Role;
import com.jaso.admin.mapper.AdminMapper;
import com.jaso.admin.service.AdminService;
import com.jaso.base.bean.IP;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by dllo on 17/12/5.
 */
@Service("adminService")
public class AdminServiceImpl implements AdminService {
    @Resource
    private AdminMapper adminMapper;

    /**
     * 通过id查询
     * @param admin_id 主键id
     * @return
     */
    @Override
    public Admin select_adminById(int admin_id) {
        return adminMapper.select_adminById(admin_id);
    }

    /**
     * 根据登录名查询
     * @param login_name 登录名
     * @return
     */
    @Override
    public Admin select_adminByLoginName(String login_name) {
        return adminMapper.select_adminByLoginName(login_name);
    }

    /**
     * 通过名字和密码查询
     * @param admin
     * @return
     */
    @Override
    public Admin select_adminByLoginNameAndPwd(Admin admin) {
        return adminMapper.select_adminByLoginNameAndPwd(admin);
    }

    /**
     * 添加 IP 对象
     */
    @Override
    public void insertIP(IP ip) {
        adminMapper.insert_IP(ip);
    }

    /**
     * 根据 IP地址查询
     */
    @Override
    public IP select_IPByName(String ip_name) {
        return adminMapper.select_IPByName(ip_name);
    }

    @Override
    public void updata_IPLoginAc(IP ip) {
        adminMapper.update_IPLoginAcc(ip);
    }

    @Override
    public List<Admin> select_allAdmin() {
        return adminMapper.select_allAdmin();
    }

    @Override
    public List<Admin> select_adminByRoleId(int role_id) {
        return adminMapper.select_adminByRoleId(role_id);
    }
}
