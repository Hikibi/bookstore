package com.lanou.bookstore.book.dao.impl;

import com.lanou.bookstore.book.dao.BookDao;
import com.lanou.bookstore.book.domain.Book;

import com.lanou.jdbc.GxQueryRunner;
import com.lanou.jdbc.JdbcUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;


import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by dllo on 17/9/21.
 */
public class BookDaoImpl implements BookDao{

    private QueryRunner qr = new GxQueryRunner();

    @Override
    public List<Book> findAll() {
        try {


            String sql = "SELECT * FROM book WHERE 1=1 AND del=1";
//            image, bname

            return qr.query(sql, new BeanListHandler<>(Book.class));

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Book load(String bid) {
        try {
            String sql = "SELECT * FROM book WHERE 1=1 AND bid=? AND del=1";

//            image, bname


            return qr.query( sql, new BeanHandler<>(Book.class), bid);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Book> findByCategory(String cid) {
        try {

            String sql = "SELECT * FROM book WHERE 1=1 AND cid=? AND del=1";

            return qr.query(sql, new BeanListHandler<>(Book.class), cid);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void delete(String bid) {
        try {
            String sql = "UPDATE book SET del=0 WHERE bid=?";
            qr.update(sql, bid);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void mod(Book book) {
        try {
            String sql = "UPDATE book SET bname=?, price=?, author=?, cid=? WHERE bid=?";
            Object[] params = {book.getBname(), book.getPrice(), book.getAuthor(),
                            book.getCid(), book.getBid()};
            qr.update(sql, params);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

//    高级搜索图书
    public List<Book> advanced(String bname) {
        try {


            String sql = "SELECT * FROM book WHERE bname LIKE ?";

            return qr.query(sql, new BeanListHandler<>(Book.class), "%" + bname + "%");

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


}
