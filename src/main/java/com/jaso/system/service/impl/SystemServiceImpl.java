package com.jaso.system.service.impl;

import com.jaso.admin.bean.Admin;
import com.jaso.system.mapper.SystemMapper;
import com.jaso.system.service.SystemService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by dllo on 17/12/8.
 */
@Service("systemService")
public class SystemServiceImpl implements SystemService {

    @Resource
    private SystemMapper systemMapper;


    @Override
    public void bind(Admin admin) {
        systemMapper.bind(admin);
    }

    @Override
    public int bindState(Integer id) {
        String bind = systemMapper.bindState(id);
        int i = Integer.parseInt(bind.substring(0,1));
        return i;
    }

    @Override
    public void ediAdmin(Admin admin) {
        systemMapper.ediAdmin(admin);
    }

    @Override
    public void modifyPWD(Admin admin) {
        systemMapper.modifyPWD(admin);
    }
}
