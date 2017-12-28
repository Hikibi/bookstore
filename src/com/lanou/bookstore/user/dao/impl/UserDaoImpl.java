package com.lanou.bookstore.user.dao.impl;

import com.lanou.bookstore.user.dao.UserDao;
import com.lanou.bookstore.user.domain.User;
import com.lanou.jdbc.GxQueryRunner;
import com.lanou.mail.Mail;
import com.lanou.mail.MailUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.junit.Test;

import javax.mail.MessagingException;
import javax.mail.Session;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by dllo on 17/9/21.
 */
public class UserDaoImpl implements UserDao {
    private QueryRunner qr = new GxQueryRunner();

    //    添加用户
    @Override
    public void add(User u) {
        try {
            String sql = "INSERT INTO tb_user VALUES(?,?,?,?,?,?)";
            Object[] params = {u.getUid(), u.getUsername(), u.getPassword(),
                    u.getEmail(), u.getCode(), u.isState()};
            qr.update(sql, params);

            new Thread(() -> sendMail(u.getEmail(), u.getCode())).start();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    //    修改用户的激活状态
    @Override
    public void activated(String code) {
        try {
            String sql = "UPDATE tb_user SET state=true WHERE code=?";
            qr.update(sql, code);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //    判断 username 时候存在  (查重)
    @Test
    public boolean nameJudge(String username) {
        try {

            String sql = "SELECT * FROM tb_user WHERE username=?";

            User query = qr.query(sql, new BeanHandler<>(User.class), username);

            boolean state = true;

            if (query == null) {
                state = false;
            }

            return state;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    //    判断 用户是否已经激活, 防止多次激活
    public boolean stateJudge(String code) {
        try {

            String sql = "SELECT state FROM tb_user WHERE code=?";
            User state = qr.query(sql, new BeanHandler<>(User.class), code);

            return state.isState();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    //    判断用户名和密码是否对应
    @Override
    public boolean namePasswordJudge(String username, String password) {
        try {

            String sql = "SELECT * FROM tb_user WHERE username=? AND password=?";
            Object[] params = {username, password};

            User query = qr.query(sql, new BeanHandler<>(User.class), params);

            boolean state = true;

            if (query == null) {
                state = false;
            }

            return state;

        } catch (SQLException e) {
            e.printStackTrace();
        }


        return false;
    }

    //    用户是否处于激活状态
    @Override
    public boolean usernamePasswordStateJudge(String username, String password) {
        try {

            String sql = "SELECT * FROM tb_user WHERE username=? AND password=?";
            Object[] params = {username, password};

            User query = qr.query(sql, new BeanHandler<>(User.class), params);

            boolean state;

            if (query == null) {
                state = false;
            } else {
                state = query.isState();
            }

            return state;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    //    通过用户名查询 uid
    public String uidToUsername(String username) {
        try {

            String sql = "SELECT * FROM tb_user WHERE username=?";
            User user = qr.query(sql, new BeanHandler<>(User.class), username);

            return user.getUid();


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    //    通过 uid 查询 用户名
    public String usernameToUid(String uid) {
        try {

            String sql = "SELECT * FROM tb_user WHERE uid=?";
            User user = qr.query(sql, new BeanHandler<>(User.class), uid);

            return user.getUsername();


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }




    //    通过 uid 查询 全部信息
    public User uidToAll(String uid) {
        try {

            String sql = "SELECT * FROM tb_user WHERE uid=?";

            return qr.query(sql, new BeanHandler<>(User.class), uid);


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    //    发送邮件给用户
    private void sendMail(String email, String code) {
        try {
            Session session = MailUtils.createSession("smtp.163.com", "15842209819@163.com", "xxl101354@");
            Mail mail = new Mail("15842209819@163.com", email, "点击验证",
                    "<a href='http://localhost:8080/jsps/user/activated.jsp?code=" + code + "'>点击验证</a>"
                            + "或者点击http://localhost:8080/jsps/user/activated.jsp?code=" + code);
            MailUtils.send(session, mail);
        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
