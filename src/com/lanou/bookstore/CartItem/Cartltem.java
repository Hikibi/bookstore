package com.lanou.bookstore.CartItem;

import com.lanou.bookstore.book.domain.Book;
import com.lanou.bookstore.book.service.impl.BookServiceImpl;

/**
 * Created by dllo on 17/9/21.
 */
public class Cartltem {
    private Book book;
    private int count;

    @Override
    public String toString() {
        return "Cartltem{" +
                "book=" + book +
                ", count=" + count +
                '}';
    }

    public Cartltem(Book book, int count) {
        this.book = book;
        this.count = count;
    }

    public Cartltem() {
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
