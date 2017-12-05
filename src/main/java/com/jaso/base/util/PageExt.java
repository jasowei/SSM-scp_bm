package com.jaso.base.util;

/**
 * Created by dllo on 17/11/14.
 */
public class PageExt {
    private int pc;//当前页
    private int ps;//每页记录数
    private String sort;//顺序
    private String column;//列名

    public PageExt() {
    }

    public PageExt(int pc, int ps, String sort, String column) {
        this.pc = pc;
        this.ps = ps;
        this.sort = sort;
        this.column = column;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getColumn() {
        return column;
    }

    public void setColumn(String column) {
        this.column = column;
    }

    @Override
    public String toString() {
        return "PageExt{" +
                "pc=" + pc +
                ", ps=" + ps +
                ", sort='" + sort + '\'' +
                ", column='" + column + '\'' +
                '}';
    }

    public int getPc() {
        return pc;
    }

    public void setPc(int pc) {
        this.pc = pc;
    }

    public int getPs() {
        return ps;
    }

    public void setPs(int ps) {
        this.ps = ps;
    }
}
