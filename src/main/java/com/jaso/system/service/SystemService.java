package com.jaso.system.service;

import com.jaso.admin.bean.Admin;

/**
 * Created by dllo on 17/12/8.
 */
public interface SystemService {
    void bind(Admin admin);

    int bindState(Integer id);

    void ediAdmin(Admin admin);

    void modifyPWD(Admin admin);

}
