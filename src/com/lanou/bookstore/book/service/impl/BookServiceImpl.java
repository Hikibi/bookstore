package com.lanou.bookstore.book.service.impl;

import com.lanou.bookstore.book.dao.impl.BookDaoImpl;
import com.lanou.bookstore.book.domain.Book;
import com.lanou.bookstore.book.service.BookService;

import java.util.List;

/**
 * Created by dllo on 17/9/21.
 */
public class BookServiceImpl implements BookService{

    private BookDaoImpl bookDao = new BookDaoImpl();

    @Override
    public List<Book> findAll() {
        return bookDao.findAll();
    }

    @Override
    public Book load(String bid) {
        return bookDao.load(bid);
    }

    @Override
    public List<Book> findByCategory(String cid) {
        return bookDao.findByCategory(cid);
    }

    @Override
    public void delete(String bid) {
        bookDao.delete(bid);
    }

    @Override
    public void mod(Book book) {
        bookDao.mod(book);
    }

    @Override
    public List<Book> advanced(String bname) {
        return bookDao.advanced(bname);
    }
}
