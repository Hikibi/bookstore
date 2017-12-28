package com.lanou.bookstore.user.service;

import com.lanou.bookstore.user.dao.impl.UserDaoImpl;
import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by dllo on 17/9/8.
 */
public class UserJudge {
    private UserDaoImpl userDao = new UserDaoImpl();

    //    判断名字
    @Test
    public int name(String name) {

        Pattern pat = Pattern.compile("\\W|\\s");
        Matcher matcher = pat.matcher(name);

        if (name.length() == 0) {
//            名字为空, 返回 1
            return 1;
        } else if (name.length() >= 15) {
//            名字长度大于 15, 返回 2
            return 2;
        } else if (userDao.nameJudge(name)) {
            return 3;
//            非法字符
        } else if (matcher.find()) {
            return 4;
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

    //    判断邮箱
    public int email(String email) {
        String regex = "(\\w|-)+@\\w+\\.\\w+";
        if (!(email.matches(regex))) {
            return 1;
        }
        return 0;
    }

    //    判断 激活状态
    public int state(String code) {
        if (userDao.stateJudge(code)) {
            return 1;
        }
        return 0;
    }

    public int number(String number) {
        String regex = "(^\\d{18}$)|(^\\d{15}$)";
        if (!(number.matches(regex))) {
//            身份证号不符合条件返回 1
            return 1;
        }
//        身份证号符合条件返回 0
        return 0;
    }

    //    判断密码是否错误
    public int usernameAndPassword(String username, String password) {
        if (!(userDao.namePasswordJudge(username, password))) {
//            密码错误 返回 1
            return 1;
        }
        return 0;
    }

    //    判断是否处于激活状态
    public int usernamePasswordState(String username, String password) {
        if (!(userDao.usernamePasswordStateJudge(username, password))) {
//            未激活状态 返回 1
            return 1;
        }
        return 0;
    }

    //    判断验证码是否相等
    public int verifyServlet(String code, String verify) {

        if (code.toUpperCase().equals(verify.toUpperCase())) {
//            相等返回 0
            return 0;
        }
//        不相等返回 1
        return 1;

    }

}
