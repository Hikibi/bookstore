package com.lanou.bookstore.admin.service;

import com.lanou.bookstore.admin.dao.AdminDao;

/**
 * Created by dllo on 17/9/23.
 */
public class AdminJudge {

    private AdminDao adminDao = new AdminDao();

    public int name(String name) {
        if (name.length() == 0) {
//            名字为空, 返回 1
            return 1;
        } else if (name.length() >= 15) {
//            名字长度大于 15, 返回 2
            return 2;
        }
//        名字符合条件返回 0
        return 0;
    }

    //    判断密码
    public int password(String password) {

        if (password.length() == 0) {
//            密码为0, 返回 1
            return 1;
        }
//        密码正确放回 0
        return 0;
    }

    //    判断密码是否错误
    public int usernameAndPassword(String username, String password){
        if(!(adminDao.namePasswordJudge(username, password))){
//            密码错误 返回 1
            return 1;
        }
        return 0;
    }

}
