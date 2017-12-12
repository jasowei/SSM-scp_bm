package com.jaso.admin.bean;

import java.util.Date;
import java.util.List;

/**
 * Created by dllo on 17/12/5.
 */
public class Admin {
    
    private int admin_id;//管理员id
    private String login_name;//登录名
    private String password;//密码
    private String real_name;//真实姓名
    private String gender;//性别
    private String title_url;//路径
    private String telephone;//电话
    private String email;//email
    private String address;//地址
    private List<Role> roles;//角色
    private Date create_date;//创建时间

    public Admin(String login_name, String password, String real_name, String gender, String title_url, String telephone, String email, String address) {
        this.login_name = login_name;
        this.password = password;
        this.real_name = real_name;
        this.gender = gender;
        this.title_url = title_url;
        this.telephone = telephone;
        this.email = email;
        this.address = address;
    }

    public Admin() {
    
    }

    public Admin(String login_name, String password) {
        this.login_name = login_name;
        this.password = password;
    }

    public Admin(int admin_id, String login_name, String password, String real_name, String gender, String title_url, String telephone, String email, String address) {

        this.admin_id = admin_id;
        this.login_name = login_name;
        this.password = password;
        this.real_name = real_name;
        this.gender = gender;
        this.title_url = title_url;
        this.telephone = telephone;
        this.email = email;
        this.address = address;
    }

    public Admin(int admin_id, String login_name, String password, String real_name, String gender, String title_url, String telephone, String email, String address, List<Role> roles) {
        this.admin_id = admin_id;
        this.login_name = login_name;
        this.password = password;
        this.real_name = real_name;
        this.gender = gender;
        this.title_url = title_url;
        this.telephone = telephone;
        this.email = email;
        this.address = address;
        this.roles = roles;
    }

    public Admin(int admin_id, String login_name, String password, String real_name, String gender, String title_url, String telephone, String email, String address, List<Role> roles, Date create_date) {
        this.admin_id = admin_id;
        this.login_name = login_name;
        this.password = password;
        this.real_name = real_name;
        this.gender = gender;
        this.title_url = title_url;
        this.telephone = telephone;
        this.email = email;
        this.address = address;
        this.roles = roles;
        this.create_date = create_date;
    }

    public Admin(String login_name, String password, String real_name, String gender, String title_url, String telephone, String email, String address, List<Role> roles, Date create_date) {
        this.login_name = login_name;
        this.password = password;
        this.real_name = real_name;
        this.gender = gender;
        this.title_url = title_url;
        this.telephone = telephone;
        this.email = email;
        this.address = address;
        this.roles = roles;
        this.create_date = create_date;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "admin_id=" + admin_id +
                ", login_name='" + login_name + '\'' +
                ", password='" + password + '\'' +
                ", real_name='" + real_name + '\'' +
                ", gender='" + gender + '\'' +
                ", title_url='" + title_url + '\'' +
                ", telephone='" + telephone + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", roles=" + roles +
                ", create_date=" + create_date +
                '}';
    }

    public Date getCreate_date() {
        return create_date;
    }

    public void setCreate_date(Date create_date) {
        this.create_date = create_date;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public int getAdmin_id() {
        return admin_id;
    }

    public void setAdmin_id(int admin_id) {
        this.admin_id = admin_id;
    }

    public String getLogin_name() {
        return login_name;
    }

    public void setLogin_name(String login_name) {
        this.login_name = login_name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getReal_name() {
        return real_name;
    }

    public void setReal_name(String real_name) {
        this.real_name = real_name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getTitle_url() {
        return title_url;
    }

    public void setTitle_url(String title_url) {
        this.title_url = title_url;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
