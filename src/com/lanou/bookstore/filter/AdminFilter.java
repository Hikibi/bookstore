package com.lanou.bookstore.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Created by dllo on 17/9/30.
 */
//@WebFilter(filterName = "AdminFilter", urlPatterns = "/*")
public class AdminFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
                HttpServletRequest request = (HttpServletRequest) req;
        String username = (String) request.getSession().getAttribute("username");

        if (username == null) {
            req.setAttribute("msg", "本动漫之家讨厌乱进入的你, 拜拜了你");
            req.getRequestDispatcher("/jsps/msg.jsp").forward(req, resp);
        } else {
            chain.doFilter(req, resp);
        }
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
