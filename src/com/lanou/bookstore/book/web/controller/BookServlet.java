package com.lanou.bookstore.book.web.controller;

import com.lanou.bookstore.book.domain.Book;
import com.lanou.bookstore.book.service.impl.BookServiceImpl;
import com.lanou.servlet.BaseServlet;
import com.lanou.servlet.BasicServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by dllo on 17/9/21.
 */
@WebServlet("/BookServlet")
public class BookServlet extends BasicServlet {

    private BookServiceImpl bookService = new BookServiceImpl();

    public String findAll(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setAttribute("all", bookService.findAll());

        return "f:/jsps/book/list.jsp";

    }

    public String load(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

//        for (Book b : bookService.load(request.getParameter("bid"))) {
//            System.out.println(b.getBname());
//        }



      Book book = bookService.load(request.getParameter("bid"));
        request.setAttribute("load",book);


        return "f:/jsps/book/desc.jsp";

    }

    public String findByCategory(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {


         request.setAttribute("all", bookService.findByCategory(request.getParameter("cid")));

        return "f:/jsps/book/list.jsp";

    }

//    高级搜索图书
    public String advanced(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        System.out.println("111111");
        System.out.println(request.getParameter("bname"));

        request.setAttribute("all", bookService.advanced(request.getParameter("bname")));

        return "f:/jsps/book/list.jsp";
    }



}