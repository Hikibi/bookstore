package com.lanou.bookstore.admin.domain;

/**
 * Created by dllo on 17/9/23.
 */
public class Category {

    private String cid;
    private String cname;
    private String del;

    public Category(String cid, String cname, String del) {
        this.cid = cid;
        this.cname = cname;
        this.del = del;
    }

    public Category() {
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getDel() {
        return del;
    }

    public void setDel(String del) {
        this.del = del;
    }

    @Override
    public String toString() {
        return "Category{" +
                "cid='" + cid + '\'' +
                ", cname='" + cname + '\'' +
                ", del='" + del + '\'' +
                '}';
    }
}

