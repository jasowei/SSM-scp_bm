package com.jaso.admin.service;

import com.jaso.admin.bean.Admin;

/**
 * Created by dllo on 17/12/5.
 */
public interface AdminService {


    Admin select_adminById(int admin_id);

    Admin select_adminByLoginName(String login_name);

    Admin select_adminByLoginNameAndPwd(Admin admin);
}
