package com.jaso.base.mapper;

import com.jaso.base.bean.Menu;
import com.jaso.base.util.PageBean;
import com.jaso.base.util.PageExt;

import java.util.List;

/**
 * Created by dllo on 17/12/6.
 */
public interface MenuMapper {
    List<Menu> findAll();

    Menu select_menuById(Integer id);

    List<Menu> findAllParentMenu();

    int menuSize();

    void addMenu(Menu menu);


    Menu select_menuByName(String menu_name);

    void delMenu(Integer id);

    List<Menu> hasSubMenu(Integer id);

    List<Menu> findAllDel();

    void rcoMenu(Integer id);

    void ediMenu(Menu menu);
}
