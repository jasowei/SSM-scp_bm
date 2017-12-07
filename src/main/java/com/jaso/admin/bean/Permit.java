package com.jaso.admin.bean;

/**
 * Created by dllo on 17/12/7.
 */
public class Permit {

    private int permit_id;//权限id
    private String permit_name;//权限名称

    public Permit() {
    }

    public Permit(String permit_name) {
        this.permit_name = permit_name;
    }

    public Permit(int permit_id, String permit_name) {
        this.permit_id = permit_id;
        this.permit_name = permit_name;
    }

    @Override
    public String toString() {
        return "Permit{" +
                "permit_id=" + permit_id +
                ", permit_name='" + permit_name + '\'' +
                '}';
    }

    public int getPermit_id() {
        return permit_id;
    }

    public void setPermit_id(int permit_id) {
        this.permit_id = permit_id;
    }

    public String getPermit_name() {
        return permit_name;
    }

    public void setPermit_name(String permit_name) {
        this.permit_name = permit_name;
    }
}
