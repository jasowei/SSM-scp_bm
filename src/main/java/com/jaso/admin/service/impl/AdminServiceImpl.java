package com.jaso.admin.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jaso.admin.bean.Admin;
import com.jaso.admin.lusence.Index;
import com.jaso.admin.lusence.Search;
import com.jaso.admin.mapper.AdminMapper;
import com.jaso.admin.service.AdminService;
import com.jaso.base.bean.IP;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
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
     *
     * @param admin_id 主键id
     * @return
     */
    @Override
    public Admin select_adminById(int admin_id) {
        return adminMapper.select_adminById(admin_id);
    }

    /**
     * 根据登录名查询
     *
     * @param login_name 登录名
     * @return
     */
    @Override
    public Admin select_adminByLoginName(String login_name) {
        return adminMapper.select_adminByLoginName(login_name);
    }

    /**
     * 通过名字和密码查询
     *
     * @param admin
     * @return
     */
    @Override
    public Admin select_adminByLoginNameAndPwd(Admin admin) {
        createIndex();
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
    public PageInfo<Admin> select_allAdmin(Integer pageNum, Integer pageSize) {

        PageHelper.startPage(pageNum, pageSize);
        List<Admin> admins = adminMapper.select_allAdminNoRole();
        PageInfo<Admin> pageInfo = new PageInfo<Admin>(admins);

        return pageInfo;
    }

    @Override
    public List<Admin> select_adminByRoleId(int role_id) {
        return adminMapper.select_adminByRoleId(role_id);
    }

    public static final String REGEX_MOBILE = "(^((13[0-9])|(15[^4,\\D])|(17[0-9])|(18[0-9]))\\d{8}$)||(\\d{3}-\\d{8})||(\\d{4}-\\d{7})";
    public static final String REGEX_EMAIL = "^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$";

    @Override
    public List<Admin> search(String keyWord) {
        if (keyWord.trim().equals("")) {
            return adminMapper.select_allAdmin();
        } else {
            Search search = new Search();
            List<String> s = search.search(keyWord);
            List<Admin> adminList = new ArrayList<>();

            if (keyWord.matches(REGEX_EMAIL)) {
                for (String s1 : s) {
                    Admin admin = adminMapper.findByEmail(s1);
                    adminList.add(admin);
                }
            } else if (keyWord.matches(REGEX_MOBILE)) {
                for (String s1 : s) {
                    Admin admin = adminMapper.select_adminByTel(s1);
                    adminList.add(admin);
                }
            } else {
                for (String s1 : s) {
                    Admin admin = adminMapper.select_adminByLoginName(s1);
                    adminList.add(admin);
                }
            }
            return adminList;
        }
    }

    @Override
    public Admin select_adminByTel(String telephone) {
        return adminMapper.select_adminByTel(telephone);
    }

    private void createIndex() {
        Index index = new Index();
        index.index(adminMapper);
    }


}
