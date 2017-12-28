package com.lanou.bookstore.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * Created by dllo on 17/9/25.
 */
//@WebFilter(filterName = "UserFilter", urlPatterns = "/jsps/cart/*")
public class UserFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {

            req.setAttribute("msg", "网页可能出现了一些问题, 即将自动跳转回主页");
            req.getRequestDispatcher("/jsps/msg.jsp").forward(req, resp);

    }

    public void init(FilterConfig config) throws ServletException {

    }

}
