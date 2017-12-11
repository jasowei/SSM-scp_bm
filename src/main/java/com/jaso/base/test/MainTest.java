package com.jaso.base.test;

import com.jaso.admin.bean.Admin;
import com.jaso.admin.bean.Role;
import com.jaso.admin.mapper.AdminMapper;
import com.jaso.admin.mapper.RoleMapper;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * Created by dllo on 17/12/5.
 */
public class MainTest {
    private ApplicationContext context;

    @Before
    public void init(){
        // 获得启动容器
        context = new ClassPathXmlApplicationContext("spring/SSM-mybatis.xml");

    }

    /**
     * 测试框架搭建
     */
    @Test
    public void test1(){
        AdminMapper adminMapper = (AdminMapper) context.getBean("adminMapper");
        Admin admin = adminMapper.select_adminById(1);
//        System.out.println(admin);

        Admin admin1 = new Admin();
        admin.setLogin_name("admin");
        admin.setPassword("123");

        Admin admin2 = adminMapper.select_adminByLoginNameAndPwd(admin1);
        System.out.println(admin2);
    }

    @Test
    public void testAdminFindAll(){
        AdminMapper adminMapper = (AdminMapper) context.getBean("adminMapper");
//        List<Admin> adminList = adminMapper.select_allAdmin();
        List<Admin> adminList = adminMapper.select_adminByRoleId(3);

        for (Admin admin : adminList) {
            System.out.println(admin);
        }
    }

    @Test
    public void testRoleFindAll(){
        RoleMapper roleMapper = (RoleMapper) context.getBean("roleMapper");

        List<Role> roles = roleMapper.select_allRole();
        for (Role role : roles) {
            System.out.println(role);
        }

        System.out.println(roleMapper.select_RoleByName("业务员"));
    }


}
