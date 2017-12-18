package com.jaso.admin.test;

import com.jaso.admin.bean.Admin;
import com.jaso.admin.mapper.AdminMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * Created by dllo on 17/12/18.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"/spring/SSM-*.xml"})
public class MainTest {

    @Resource
    private AdminMapper adminMapper;

    @Test
    public void test(){
        System.out.println(adminMapper.select_adminByLoginName("admin"));


    }
}
