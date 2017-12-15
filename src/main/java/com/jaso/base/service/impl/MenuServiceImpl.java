package com.jaso.base.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jaso.base.bean.Menu;
import com.jaso.base.lucene.Index;
import com.jaso.base.lucene.Search;
import com.jaso.base.mapper.MenuMapper;
import com.jaso.base.service.MenuService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 17/12/6.
 */
@Service("menuService")
public class MenuServiceImpl implements MenuService {

    @Resource
    private MenuMapper menuMapper;
    Search s = new Search();


    public PageInfo<Menu> select_allmenu(Integer pageNum, Integer pageSize) {

        pageNum = pageNum == null ? 1 : pageNum;
        pageSize = pageSize == null ? 5 : pageSize;
        PageHelper.startPage(pageNum, pageSize);

        List<Menu> all = menuMapper.findAll();

        PageInfo<Menu> pageInfo = new PageInfo<Menu>(all);
        return pageInfo;
    }
    @Override
    public List<Menu> searchMenu(String search) {
        List<Menu> all = new ArrayList<Menu>();

            List<String> strings = s.search(search);
            for (String string : strings) {
                all.add(menuMapper.select_menuByName(string));
            }

        return all;
    }


    public Menu select_menuById(Integer id) {
        return menuMapper.select_menuById(id);
    }

    public List<Menu> findAllParentMenu() {
        return menuMapper.findAllParentMenu();
    }

    public int menuSize() {
        return menuMapper.menuSize();
    }

    public void addMenu(Menu menu) {
        menuMapper.addMenu(menu);
        createIndex();
    }

    public Menu select_menuByName(String menu_name) {
        return menuMapper.select_menuByName(menu_name);
    }

    public void delMenu(Integer id) {
        menuMapper.delMenu(id);
        createIndex();
    }

    public List<Menu> hasSubMenu(Integer id) {
        return menuMapper.hasSubMenu(id);
    }

    public PageInfo<Menu> select_allDelmenu(Integer pageNum, Integer pageSize) {
        pageNum = pageNum == null ? 1 : pageNum;
        pageSize = pageSize == null ? 5 : pageSize;
        PageHelper.startPage(pageNum, pageSize);
        List<Menu> all = menuMapper.findAllDel();
        PageInfo<Menu> pageInfo = new PageInfo<Menu>(all);
        return pageInfo;
    }

    public void rcoMenu(Integer id) {
        menuMapper.rcoMenu(id);
        createIndex();
    }

    public void ediMenu(Menu menu) {
        menuMapper.ediMenu(menu);
        createIndex();
    }

    @Override
    public List<Menu> findAllpMenu() {
        return menuMapper.pMenu();
    }

    @Override
    public List<Menu> findAllsMenu(Integer id) {
        return menuMapper.sMenu(id);
    }




    /*索引搜索*/
    public void createIndex() {
        Index index = new Index();
        index.index(menuMapper);
    }


}
