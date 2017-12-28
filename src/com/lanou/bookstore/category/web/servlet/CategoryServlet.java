package com.lanou.bookstore.category.web.servlet;

import com.lanou.bookstore.category.service.impl.CategoryServiceImpl;
import com.lanou.servlet.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by dllo on 17/9/21.
 */
@WebServlet("/CategoryServlet")
public class CategoryServlet extends BaseServlet {

    private CategoryServiceImpl categoryService = new CategoryServiceImpl();


    private String findAll(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        return "f:/jsps/book/list.jsp";
    }



}