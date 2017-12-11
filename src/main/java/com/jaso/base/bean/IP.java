package com.jaso.base.bean;

import java.util.Date;

/**
 * Created by dllo on 17/12/8.
 */
public class IP {

    private int ip_id;//主键id
    private String ip_name;//IP地址
    private Date login_date;//登录时间
    private int login_account;//登录次数

    public IP() {
    }

        public IP(String ip_name, Date login_date, int login_account) {
        this.ip_name = ip_name;
        this.login_date = login_date;
        this.login_account = login_account;
    }

    public IP(int ip_id, String ip_name, Date login_date, int login_account) {
        this.ip_id = ip_id;
        this.ip_name = ip_name;
        this.login_date = login_date;
        this.login_account = login_account;
    }

    public int getLogin_account() {
        return login_account;
    }

    public void setLogin_account(int login_account) {
        this.login_account = login_account;
    }

    public int getIp_id() {
        return ip_id;
    }

    public void setIp_id(int ip_id) {
        this.ip_id = ip_id;
    }

    public String getIp_name() {
        return ip_name;
    }

    public void setIp_name(String ip_name) {
        this.ip_name = ip_name;
    }

    public Date getLogin_date() {
        return login_date;
    }

    public void setLogin_date(Date login_date) {
        this.login_date = login_date;
    }

}
