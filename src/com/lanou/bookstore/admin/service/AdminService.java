package com.lanou.bookstore.admin.service;

import com.lanou.bookstore.admin.dao.AdminDao;
import com.lanou.bookstore.admin.domain.Category;
import com.lanou.bookstore.book.domain.Book;
import com.lanou.bookstore.order.domain.CountAndBooks;
import com.lanou.bookstore.order.domain.Orders;

import java.util.List;

/**
 * Created by dllo on 17/9/23.
 */
public class AdminService {

    private AdminDao adminDao = new AdminDao();

    public List<Category> findAll() {
        return adminDao.findAll();
    }

    public void revise(String cname, String cid) {
        adminDao.revise(cname, cid);
    }

    public void del(String cid) {
        adminDao.del(cid);
    }

    public void add(String cname, String cid) {
        adminDao.add(cname, cid);
    }

    public void addBook(Book book) {
        adminDao.addBook(book);
    }

    public List<Orders> findAllOrder() {
        return adminDao.findAllOrder();
    }

    public List<Orders> findAnyOrder(String state) {
        return adminDao.findAnyOrder(state);
    }

    public void sendAny(String oid) {
        adminDao.sendAny(oid);
    }
}
