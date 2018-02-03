package com.lanou.bookstore.user.web.controller;

import com.lanou.bookstore.CartItem.Cart;
import com.lanou.bookstore.user.domain.User;
import com.lanou.bookstore.user.service.UserJudge;
import com.lanou.bookstore.user.service.impl.UserServiceImpl;
import com.lanou.commons.CommonUtils;
import com.lanou.servlet.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;

/**
 * Created by dllo on 18/9/21.
 */
@WebServlet("/UserServlet")
public class UserServlet extends BaseServlet implements Serializable {

    private UserServiceImpl userService = new UserServiceImpl();

    private UserJudge judge = new UserJudge();


//    用户注册
    public String add(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        User u = CommonUtils.toBean(request.getParameterMap(), User.class);

        if (judge.name(u.getUsername()) == 3) {
            request.setAttribute("msg", "输入的用户名重复");
            return "f:/jsps/user/regist.jsp";
        } else if ((judge.name(u.getUsername()) + judge.password(u.getPassword()) != 0)
                && judge.name(u.getUsername()) == 4) {
            request.setAttribute("msg", "输入的信息不符合规范");
            return "f:/jsps/user/regist.jsp";
        } else if (judge.email(u.getEmail()) == 1) {
            request.setAttribute("msg", "邮箱输入错误");
            return "f:/jsps/user/regist.jsp";
        } else if (judge.verifyServlet((String) request.getSession().getAttribute("vCode"), request.getParameter("code")) == 1){
            request.setAttribute("msg", "验证码错误");
            return "f:/jsps/user/regist.jsp";
        } else {
            u.setUid(CommonUtils.uuid());
            u.setCode(CommonUtils.uuid() + CommonUtils.uuid());
            u.setState(false);
            userService.add(u);
            request.setAttribute("msg", "请查看邮箱完成会员注册");
            return "f:/jsps/msg.jsp";
        }

    }

    //    用户登录
    public String login(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        User u = CommonUtils.toBean(request.getParameterMap(), User.class);


//        短暂生效的管理员用户, 记得修改用户名 和 密码
        if(u.getUsername().equals("admin") && u.getPassword().equals("admin")){
            return "r:/adminjsps/login.jsp";
        } else if ((judge.name(u.getUsername()) == 1 || judge.password(u.getPassword()) == 1 ||
                judge.name(u.getUsername()) == 2)) {
            request.getSession().setAttribute("msg", "用户名密码输入不符合规范");
            return "f:/jsps/user/login.jsp";
        } else if (judge.usernameAndPassword(u.getUsername(), u.getPassword()) == 1) {
            request.getSession().setAttribute("msg", "用户名或密码错误");
            return "f:/jsps/user/login.jsp";
        } else if (judge.usernamePasswordState(u.getUsername(), u.getPassword()) == 1) {
            request.getSession().setAttribute("msg", "你当前用户还未完成激活");
            return "f:/jsps/user/login.jsp";
        } else {

            request.getSession().setAttribute("username", u.getUsername());
            request.getSession().setAttribute("password", u.getPassword());
            request.getSession().removeAttribute("msg");

            if (request.getSession().getAttribute(u.getUsername()) == null) {
//            创建购物车
                Cart cart = new Cart();
                cart.setCartMap(new HashMap<>());

//                创建的购物车名字为 用户名
                request.getSession().setAttribute(u.getUsername(), cart);
                return "r:/jsps/main.jsp";

            }

            return "r:/jsps/main.jsp";
        }

    }

    //    退出功能
    public String quit(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getSession().removeAttribute("username");
        request.getSession().removeAttribute("password");
        return "r:/index.jsp";
    }


    //    禁止重复激活两遍
    public String activated(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        if (judge.state(String.valueOf(request.getAttribute("code"))) == 1) {
            request.setAttribute("msg", "你已激活, 请勿重复激活");
            return "f:/jsps/msg.jsp";
        } else {
            userService.activated((String) request.getAttribute("code"));
            request.setAttribute("msg", "激活成功");
            return "f:/jsps/msg.jsp";
        }

    }
}