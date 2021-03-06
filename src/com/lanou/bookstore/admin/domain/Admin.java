package com.lanou.bookstore.admin.domain;

/**
 * Created by dllo on 17/9/23.
 */
public class Admin {
    private String uid;
    private String username;
    private String password;

    public Admin(String uid, String username, String password) {
        this.uid = uid;
        this.username = username;
        this.password = password;
    }

    public Admin() {
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "AdminDao{" +
                "uid='" + uid + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
