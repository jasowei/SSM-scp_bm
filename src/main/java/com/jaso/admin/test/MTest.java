package com.jaso.admin.test;

import com.jaso.admin.mapper.AdminMapper;


import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.File;
import java.io.IOException;
import java.net.URL;

/**
 * Created by dllo on 17/12/13.
 */
public class MTest {

    private ApplicationContext context;

    @Before
    public void init(){
        // 获得启动容器
        context = new ClassPathXmlApplicationContext("spring/SSM-mybatis.xml");
    }

    @Test
    public void testDelete() throws IOException {
//类加载根路径
        String classPath = this.getClass().getResource("/").getPath();

//类加载根路径
        URL xmlPath = this.getClass().getClassLoader().getResource("");

//类所在工程根路径
        String proClassPath = this.getClass().getResource("").getPath();

//项目服务器脚本文件路径
        File directory = new File("");// 参数为空
        String proRootPath = directory.getCanonicalPath();

//项目服务器脚本文件路径
        String proPath = System.getProperty("user.dir")+"/index";

// 获取所有的类路径 包括jar包的路径
        String allClassPath = System.getProperty("java.class.path");

////项目部署的路径
//        String path = request.getSession().getServletContext().getRealPath("/");

        System.out.println("类加载根路径:" + classPath);
        System.out.println("类加载根路径:" + xmlPath);
        System.out.println("类所在工程路径:" + proClassPath);
        System.out.println("项目服务器脚本文件路径:" + proRootPath);
        System.out.println("项目服务器脚本文件路径:" + proPath);
        System.out.println("项目部署的路径:" + allClassPath );
//        System.out.println("获取所有的类路径:" + path );
    }

    @Test
    public void testSerch(){
        AdminMapper adminMapper = (AdminMapper) context.getBean("adminMapper");

//        serchAdmin.index(adminMapper);
//        List<Admin> ad = serchAdmin.search("admin");
//
//        for (Admin admin : ad) {
//            System.out.println(admin);
//        }

    }
}
