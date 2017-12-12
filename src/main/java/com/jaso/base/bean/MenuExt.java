package com.jaso.base.bean;

import java.util.List;

/**
 * Created by dllo on 17/12/11.
 */
public class MenuExt {
    private List<Menu> menuList;
    private String search;

    public MenuExt(List<Menu> menuList, String search) {
        this.menuList = menuList;
        this.search = search;
    }

    public List<Menu> getMenuList() {
        return menuList;
    }

    public void setMenuList(List<Menu> menuList) {
        this.menuList = menuList;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }
}
