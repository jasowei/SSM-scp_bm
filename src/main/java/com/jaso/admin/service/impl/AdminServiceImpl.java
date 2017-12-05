package com.jaso.admin.service.impl;

import com.jaso.admin.bean.Admin;
import com.jaso.admin.mapper.AdminMapper;
import com.jaso.admin.service.AdminService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by dllo on 17/12/5.
 */
@Service("adminService")
public class AdminServiceImpl implements AdminService {
    @Resource
    private AdminMapper adminMapper;


    public Admin select_adminById(int admin_id) {
        return adminMapper.select_adminById(admin_id);
    }
}
