package com.lanou.bookstore.category.dao.impl;

import com.lanou.bookstore.category.domain.Category;
import com.lanou.jdbc.GxQueryRunner;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by dllo on 17/9/21.
 */
public class CategoryDaoImpl {

    private QueryRunner qr = new GxQueryRunner();

    public List<Category> findAll() {
        try {


            String sql = "SELECT * FROM category WHERE 1=1 AND del=1";
//            image, bname

            return qr.query(sql, new BeanListHandler<>(Category.class));

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


}
