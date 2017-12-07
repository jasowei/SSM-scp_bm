package com.jaso.admin.mapper;

import com.jaso.admin.bean.Admin;
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
}
