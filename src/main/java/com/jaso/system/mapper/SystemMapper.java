package com.jaso.system.mapper;

import com.jaso.admin.bean.Admin;

/**
 * Created by dllo on 17/12/8.
 */
public interface SystemMapper {
    void bind(Admin admin);

    String bindState(Integer id);

    void ediAdmin(Admin admin);

    void modifyPWD(Admin admin);
}
