package com.jaso.base.bean;

import java.util.Date;

/**
 * Created by dllo on 17/12/6.
 */
public class Menu {
    private int menu_id;//id
    private int parent_id;//父标签id
    private String icon;//图标
    private String menu_name;//菜单名称
    private String urlkey;//菜单key
    private String url;//链接地址
    private int status;//状态
    private int type;//类型
    private int sort;//排序
    private int level;//级别

    private Date create_time;//创建时间
    private int create_id;//创建者id
    private Date update_time;//更新时间
    private int update_id;//更新者id

    public Menu(int menu_id, int parent_id, String menu_name, String urlkey, String url, int status, int type, int sort, int level, Date create_time, int create_id) {
        this.menu_id = menu_id;
        this.parent_id = parent_id;
        this.menu_name = menu_name;
        this.urlkey = urlkey;
        this.url = url;
        this.status = status;
        this.type = type;
        this.sort = sort;
        this.level = level;
        this.create_time = create_time;
        this.create_id = create_id;
    }

    public Menu(int parent_id, String icon, String menu_name, String urlkey, String url, int status, int type, int sort, int level, Date create_time, int create_id) {
        this.parent_id = parent_id;
        this.icon = icon;
        this.menu_name = menu_name;
        this.urlkey = urlkey;
        this.url = url;
        this.status = status;
        this.type = type;
        this.sort = sort;
        this.level = level;
        this.create_time = create_time;
        this.create_id = create_id;
    }

    public Menu(int parent_id, String icon, String menu_name, String urlkey, String url, int status, int type, int sort, int level, Date create_time, int create_id, Date update_time, int update_id) {
        this.parent_id = parent_id;
        this.icon = icon;
        this.menu_name = menu_name;
        this.urlkey = urlkey;
        this.url = url;
        this.status = status;
        this.type = type;
        this.sort = sort;
        this.level = level;
        this.create_time = create_time;
        this.create_id = create_id;
        this.update_time = update_time;
        this.update_id = update_id;
    }

    public Menu() {

    }

    public Menu(int menu_id, int parent_id, String icon, String menu_name, String urlkey, String url, int status, int type, int sort, int level, Date create_time, int create_id, Date update_time, int update_id) {

        this.menu_id = menu_id;
        this.parent_id = parent_id;
        this.icon = icon;
        this.menu_name = menu_name;
        this.urlkey = urlkey;
        this.url = url;
        this.status = status;
        this.type = type;
        this.sort = sort;
        this.level = level;
        this.create_time = create_time;
        this.create_id = create_id;
        this.update_time = update_time;
        this.update_id = update_id;
    }

    public Menu(int menu_id, int parent_id, String icon, String menu_name, String urlkey, String url, int status, int type, int sort, int level, Date create_time, int create_id) {
        this.menu_id = menu_id;
        this.parent_id = parent_id;
        this.icon = icon;
        this.menu_name = menu_name;
        this.urlkey = urlkey;
        this.url = url;
        this.status = status;
        this.type = type;
        this.sort = sort;
        this.level = level;
        this.create_time = create_time;
        this.create_id = create_id;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "menu_id=" + menu_id +
                ", parent_id=" + parent_id +
                ", icon='" + icon + '\'' +
                ", menu_name='" + menu_name + '\'' +
                ", urlkey='" + urlkey + '\'' +
                ", url='" + url + '\'' +
                ", status=" + status +
                ", type=" + type +
                ", sort=" + sort +
                ", level=" + level +
                ", create_time=" + create_time +
                ", create_id=" + create_id +
                ", update_time=" + update_time +
                ", update_id=" + update_id +
                '}';
    }

    public int getMenu_id() {
        return menu_id;
    }

    public void setMenu_id(int menu_id) {
        this.menu_id = menu_id;
    }

    public int getParent_id() {
        return parent_id;
    }

    public void setParent_id(int parent_id) {
        this.parent_id = parent_id;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getMenu_name() {
        return menu_name;
    }

    public void setMenu_name(String menu_name) {
        this.menu_name = menu_name;
    }

    public String getUrlkey() {
        return urlkey;
    }

    public void setUrlkey(String urlkey) {
        this.urlkey = urlkey;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public int getCreate_id() {
        return create_id;
    }

    public void setCreate_id(int create_id) {
        this.create_id = create_id;
    }

    public Date getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(Date update_time) {
        this.update_time = update_time;
    }

    public int getUpdate_id() {
        return update_id;
    }

    public void setUpdate_id(int update_id) {
        this.update_id = update_id;
    }
}
