package com.jaso.base.test;

import com.jaso.admin.bean.Admin;
import com.jaso.admin.mapper.AdminMapper;
import com.jaso.base.bean.Menu;
import com.jaso.base.lucene.Index;
import com.jaso.base.lucene.Search;
import com.jaso.base.service.MenuService;
import com.lanou.mail.Mail;
import com.lanou.mail.MailUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.Session;
import java.io.IOException;
import java.util.List;

/**
 * Created by dllo on 17/12/5.
 */
@RunWith(SpringJUnit4ClassRunner.class)  //使用junit4进行测试
@ContextConfiguration
        ({"/spring/SSM-*.xml"}) //加载配置文件
public class MainTest {
    private ApplicationContext context;
    private AdminMapper adminMapper;

    @Before
    public void init(){
        // 获得启动容器
//        context = new ClassPathXmlApplicationContext("spring/SSM-mybatis.xml");

    }

    /**
     * 测试框架搭建
     */
    @Test
    public void test1(){
        adminMapper = (AdminMapper) context.getBean("adminMapper");
        Admin admin = adminMapper.select_adminById(1);
        System.out.println(admin);

        Admin admin1 = new Admin();
        admin.setLogin_name("admin");
        admin.setPassword("123");

        Admin admin2 = adminMapper.select_adminByLoginNameAndPwd(admin1);
        System.out.println(admin2);
    }

    @Test
    public void test2()  {
//        Index index = new Index();
//        index.index();

        Search search = new Search();
        search.search("菜单啊");
    }

    @Test
    public void test3(){
        adminMapper = (AdminMapper) context.getBean("adminMapper");
        Admin admin = adminMapper.select_adminByLoginName("aaa");
        System.out.println(admin);
    }

    @Resource
    private MenuService menuService;

    @Test
    public void test4(){
        List<Menu> menuList = menuService.searchMenu("管理1");
        for (Menu menu : menuList) {
            System.out.println(menu);

        }
    }

}
