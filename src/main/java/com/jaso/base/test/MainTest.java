package com.jaso.base.test;

import com.jaso.admin.bean.Admin;
import com.jaso.admin.mapper.AdminMapper;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

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
        System.out.println(admin);
    }

}
