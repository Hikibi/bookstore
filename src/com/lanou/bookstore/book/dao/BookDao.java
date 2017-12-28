package com.lanou.bookstore.book.dao;

import com.lanou.bookstore.book.domain.Book;

import java.util.List;

/**
 * Created by dllo on 17/9/21.
 */
public interface BookDao {

    List<Book> findAll();

    Book load(String bid);

    List<Book> findByCategory(String cid);

    void delete(String bid);

    void mod(Book book);
}
