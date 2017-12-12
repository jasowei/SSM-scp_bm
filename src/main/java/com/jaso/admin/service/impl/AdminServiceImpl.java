package com.jaso.admin.service.impl;

import com.jaso.admin.bean.Admin;
import com.jaso.admin.mapper.AdminMapper;
import com.jaso.admin.service.AdminService;
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
//        List<Admin> admins = adminMapper.select_adminByLoginNameAndPwd(admin);
//        return admins!= null && admins.size()>0?admins.get(0):null;
        return adminMapper.select_adminByLoginNameAndPwd(admin);
    }

    @Override
    public Admin findByEmail(String email) {
        return adminMapper.findByEmail(email);
    }

    @Override
    public void updateByEmail(String email) {
        adminMapper.updateByEmail(email);
    }
}
