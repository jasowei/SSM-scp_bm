package com.jaso.base.service;

import com.github.pagehelper.PageInfo;
import com.jaso.base.bean.Menu;

import java.util.List;

/**
 * Created by dllo on 17/12/6.
 */
public interface MenuService {
    PageInfo<Menu> select_allmenu(Integer pageNum, Integer pageSize,String search);

    Menu select_menuById(Integer id);

    List<Menu> findAllParentMenu();

    int menuSize();

    void addMenu(Menu menu);

    Menu select_menuByName(String menu_name);

    void delMenu(Integer id);

    List<Menu> hasSubMenu(Integer id);

    PageInfo<Menu> select_allDelmenu(Integer pageNum, Integer pageSize);

    void rcoMenu(Integer id);

    void ediMenu(Menu menu);

    List<Menu> findAllpMenu();

    List<Menu> findAllsMenu(Integer id);

//    List<Menu> searchMenu(String search);

}
