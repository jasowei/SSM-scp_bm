package com.jaso.base.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jaso.base.bean.Menu;
import com.jaso.base.mapper.MenuMapper;
import com.jaso.base.service.MenuService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by dllo on 17/12/6.
 */
@Service("menuService")
public class MenuServiceImpl implements MenuService {

    @Resource
    private MenuMapper menuMapper;

    @Override
    public PageInfo<Menu> select_allmenu(Integer pageNum, Integer pageSize) {
        pageNum = pageNum == null ? 1 : pageNum;
        pageSize = pageSize == null ? 5 : pageSize;
        PageHelper.startPage(pageNum, pageSize);
        List<Menu> all = menuMapper.findAll();
        PageInfo<Menu> pageInfo = new PageInfo<Menu>(all);
        return pageInfo;
    }

    @Override
    public Menu select_menuById(Integer id) {
        return menuMapper.select_menuById(id);
    }

    @Override
    public List<Menu> findAllParentMenu() {
        return menuMapper.findAllParentMenu();
    }

    @Override
    public int menuSize() {
        return menuMapper.menuSize();
    }

    @Override
    public void addMenu(Menu menu) {
        menuMapper.addMenu(menu);
    }

    @Override
    public Menu select_menuByName(String menu_name) {
        return menuMapper.select_menuByName(menu_name);
    }

    @Override
    public void delMenu(Integer id) {
        menuMapper.delMenu(id);
    }

    @Override
    public List<Menu> hasSubMenu(Integer id) {
        return menuMapper.hasSubMenu(id);
    }

    @Override
    public PageInfo<Menu> select_allDelmenu(Integer pageNum, Integer pageSize) {
        pageNum = pageNum == null ? 1 : pageNum;
        pageSize = pageSize == null ? 5 : pageSize;
        PageHelper.startPage(pageNum, pageSize);
        List<Menu> all = menuMapper.findAllDel();
        PageInfo<Menu> pageInfo = new PageInfo<Menu>(all);
        return pageInfo;
    }

    @Override
    public void rcoMenu(Integer id) {
        menuMapper.rcoMenu(id);
    }

    @Override
    public void ediMenu(Menu menu) {
        menuMapper.ediMenu(menu);
    }
}
