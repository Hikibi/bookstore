package com.lanou.bookstore.admin.dao;

import com.lanou.bookstore.admin.domain.Admin;
import com.lanou.bookstore.admin.domain.Category;
import com.lanou.bookstore.book.domain.Book;
import com.lanou.bookstore.order.domain.CountAndBooks;
import com.lanou.bookstore.order.domain.Orders;
import com.lanou.bookstore.user.domain.User;
import com.lanou.jdbc.GxQueryRunner;
import com.sun.org.apache.xpath.internal.operations.Or;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by dllo on 17/9/23.
 */
public class AdminDao {

    private QueryRunner qr = new GxQueryRunner();

    public boolean nameJudge(String username) {
        try {

            String sql = "SELECT * FROM tb_admin WHERE username=?";
            Admin admin = qr.query(sql, new BeanHandler<>(Admin.class), username);


            return admin.toString().isEmpty();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean namePasswordJudge(String username, String password) {
        try {

            String sql = "SELECT * FROM tb_admin WHERE username=? AND password=?";
            Object[] params = {username, password};

            Admin query = qr.query(sql, new BeanHandler<>(Admin.class), params);

            boolean state = true;

            if (query == null) {
                state = false;
            }

//            System.out.println(state);

            return state;


        } catch (SQLException e) {
            e.printStackTrace();
        }


        return false;
    }


    //    遍历图书菜单
    public List<Category> findAll() {
        try {


            String sql = "SELECT * FROM category WHERE del=1";
//            image, bname

            return qr.query(sql, new BeanListHandler<>(Category.class));

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    public void revise(String cname, String cid) {

        try {
            String sql = "UPDATE category SET cname='" + cname + "' WHERE cid=?";

            qr.update(sql, cid);

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void del(String cid) {

        try {
            String sql = "UPDATE category SET del=0 WHERE cid=?";

            qr.update(sql, cid);

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void add(String cname, String cid) {

        try {
            String sql = "INSERT INTO category VALUES(?, ?, 1)";

            Object[] params = {cid, cname};
            qr.update(sql, params);

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void addBook(Book book) {
        try {
            String sql = "INSERT INTO book VALUES(?,?,?,?,?,?,1)";
            Object[] params = {book.getBid(), book.getBname(), book.getPrice(),
                    book.getAuthor(), book.getImage(), book.getCid()};
            qr.update(sql, params);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //    查询全部订单
    public List<Orders> findAllOrder() {
        try {

            String sql = "SELECT * FROM orders";

//            System.out.println(query);

            return qr.query(sql, new BeanListHandler<>(Orders.class));

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    //    查询各种情况的订单情况
    public List<Orders> findAnyOrder(String state) {
        try {

            String sql = "SELECT * FROM orders WHERE state=?";

//            System.out.println(query);

            return qr.query(sql, new BeanListHandler<>(Orders.class), state);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    //    修改订单情况为已发货
    public void sendAny(String oid) {

        try {
            String sql = "UPDATE orders SET state=3 WHERE oid=?";

            qr.update(sql, oid);

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


}
