package com.lanou.bookstore.CartItem.web.servlet;

import com.lanou.bookstore.CartItem.Cart;
import com.lanou.bookstore.CartItem.Cartltem;
import com.lanou.bookstore.book.domain.Book;
import com.lanou.bookstore.book.service.impl.BookServiceImpl;
import com.lanou.bookstore.user.service.impl.UserServiceImpl;
import com.lanou.servlet.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by dllo on 17/9/21.
 */
@WebServlet("/CartServlet")
public class CartServlet extends BaseServlet {

    private BookServiceImpl bookService = new BookServiceImpl();
    private UserServiceImpl userService = new UserServiceImpl();

    public String list(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

//        得到 用户名
        String username = (String) request.getSession().getAttribute("username");
//        得到以用户名创建出的车
        Cart cart = (Cart) request.getSession().getAttribute(username);
//        获取表单中 bid 和 count
        String bid = request.getParameter("bid");
        int count = Integer.parseInt(request.getParameter("count"));
//        通过 bid 获取 book 的详细内容
        Book book = bookService.load(bid);
//        创建一个 cart 类型的 Map 对象
        Map<String, Cartltem> cartMap = cart.getCartMap();
        if (cartMap.containsKey(bid)){
            count += count;
        }
        Cartltem cartltem = new Cartltem(book, count);
        cartMap.put(bid, cartltem);



        return "f:/jsps/cart/desc.jsp";


    }


    public String add(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

//        得到 用户名
        String username = (String) request.getSession().getAttribute("username");

        if (username == null){

            request.setAttribute("msg", "你还没有登录哦 (/≧▽≦/)");

            return "f:/jsps/order/msg.jsp";

        }


//        得到以用户名创建出的车
        Cart cart = (Cart) request.getSession().getAttribute(username);

//        Cart cart = new Cart();

//        获取表单中 bid 和 count
        String bid = request.getParameter("bid");
        int count = Integer.parseInt(request.getParameter("count"));



//        通过 bid 获取 book 的详细内容
        Book book = bookService.load(bid);



//        System.out.println(request.getSession().getAttribute("bid"));


//        创建一个 cart 类型的 Map 对象
        Map<String, Cartltem> cartMap = cart.getCartMap();

        if (cartMap.containsKey(bid)){

            int oldCount = cartMap.get(bid).getCount();

            count = count + oldCount;

        }

        Cartltem cartltem = new Cartltem(book, count);

        cartMap.put(bid, cartltem);

        cart.setCartMap(cartMap);
//                                          125
        request.getSession().setAttribute(username, cart);

//        System.out.println(request.getSession().getAttribute(username));


        return "f:/jsps/cart/list.jsp";


    }

    public String clear(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String username = (String) request.getSession().getAttribute("username");


        request.getSession().removeAttribute(username);

        Cart cart = new Cart();

//        cart.getCartMap().clear();

        cart.setCartMap(new HashMap<>());


        request.getSession().setAttribute(username, cart);

        return "f:/jsps/cart/list.jsp";
    }

    public String delete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String username = (String) request.getSession().getAttribute("username");
        String bid = request.getParameter("bid");

        Cart cart = (Cart) request.getSession().getAttribute(username);
        Map<String, Cartltem> cartMap = cart.getCartMap();
        cartMap.remove(bid);


//        Cart{cartMap={1=Cartltem{book=Book{bid='1', bname='Java编程思想（第4版）',
//          price=75.6, author='qdmmy6', image='book_img/9317290-1_l.jpg', cid='1'}, count=1}}}


        return "f:/jsps/cart/list.jsp";
    }


}