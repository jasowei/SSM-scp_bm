package com.jaso.admin.bean;

import java.util.List;

/**
 * Created by dllo on 17/12/7.
 */
public class Role {

    private int role_id;//角色id
    private String role_name;//角色名
    private List<Permit> permits;//持有权限

    public Role() {
    }

    public Role(String role_name) {
        this.role_name = role_name;
    }

    public Role(int role_id, String role_name) {
        this.role_id = role_id;
        this.role_name = role_name;
    }

    public Role(int role_id, String role_name, List<Permit> permits) {
        this.role_id = role_id;
        this.role_name = role_name;
        this.permits = permits;
    }

    @Override
    public String toString() {
        return "Role{" +
                "role_id=" + role_id +
                ", role_name='" + role_name + '\'' +
                '}';
    }

    public int getRole_id() {
        return role_id;
    }

    public void setRole_id(int role_id) {
        this.role_id = role_id;
    }

    public String getRole_name() {
        return role_name;
    }

    public void setRole_name(String role_name) {
        this.role_name = role_name;
    }

    public List<Permit> getPermits() {
        return permits;
    }

    public void setPermits(List<Permit> permits) {
        this.permits = permits;
    }
}
