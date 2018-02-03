package com.lanou.bookstore.order.web.servlete;

import com.lanou.bookstore.CartItem.Cart;
import com.lanou.bookstore.CartItem.Cartltem;
import com.lanou.bookstore.order.domain.CountAndBooks;
import com.lanou.bookstore.order.domain.Orderitem;
import com.lanou.bookstore.order.domain.Orders;
import com.lanou.bookstore.order.service.OrderService;
import com.lanou.bookstore.user.domain.User;
import com.lanou.bookstore.user.service.impl.UserServiceImpl;
import com.lanou.commons.CommonUtils;
import com.lanou.mail.Mail;
import com.lanou.mail.MailUtils;
import com.lanou.servlet.BaseServlet;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

/**
 * Created by dllo on 17/9/22.
 */
@WebServlet("/OrderServlet")
public class OrderServlet extends BaseServlet {

    private Orders orders = new Orders();
    private OrderService orderService = new OrderService();

    private Orderitem orderitem = new Orderitem();

    private UserServiceImpl userService = new UserServiceImpl();


    public String add(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String username = (String) request.getSession().getAttribute("username");

//        if (username == null){
//
//            request.setAttribute("msg", "你还没有登录哦 (/≧▽≦/)");
//
//            return "f:/jsps/order/msg.jsp";
//
//        }


        Cart cart = (Cart) request.getSession().getAttribute(username);

//        赋值 oid    订单编号
        String oid = CommonUtils.uuid();
        orders.setOid(oid);

//        赋值 time   成交时间

        java.util.Date dt = new java.util.Date();
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String currentTime = sdf.format(dt);
        orders.setOrdertime(currentTime);


//        赋值 total  订单合计
        double allPrint = 0;

        Map<String, Cartltem> cartMap = cart.getCartMap();
        Iterator<Cartltem> mapIterator = cartMap.values().iterator();
        while (mapIterator.hasNext()) {
            Cartltem next = mapIterator.next();
            allPrint += next.getBook().getPrice() * next.getCount();

        }
        orders.setTotal(allPrint);

//        赋值订单状态
        orders.setState(1);

//        赋值订单的主人
        String uid = userService.uidToUsername(username);
        orders.setUid(uid);

//        订单的收获地址
        orders.setAddress("未知领域");

        if (allPrint == 0) {

            request.setAttribute("msg", "你并没有订单");
            return "f:/jsps/order/msg.jsp";

        }

        orderService.add(orders);

        Iterator<Cartltem> iterator = cartMap.values().iterator();

        while (iterator.hasNext()) {

            Cartltem next = iterator.next();
//            allPrint += next.getBook().getPrice() * next.getCount();

//            每遍历一次创建一个订单项表
//            赋值 iid    主键
            orderitem.setIid(CommonUtils.uuid());
//            赋值 count  数量
            orderitem.setCount(next.getCount());
//            赋值小计     小计
            orderitem.setSubtotal(next.getBook().getPrice() * next.getCount());
//            赋值oid     所属订单
            orderitem.setOid(oid);
//            赋值bid     订单项所指商品
            orderitem.setBid(next.getBook().getBid());

            orderService.orderitemAdd(orderitem);


        }


        List<CountAndBooks> bookShow = new LinkedList();


        System.out.println(orderitem.getBid());


        List<CountAndBooks> list = orderService.toOid(orders.getOid());

        for (CountAndBooks l : list) {

            bookShow.add(l);

        }

        request.setAttribute("orders", orders);

        request.setAttribute("bookShow", bookShow);

        request.getSession().removeAttribute(username);

        cart.setCartMap(new HashMap<>());

        request.getSession().setAttribute(username, cart);

        return "f:/jsps/order/desc.jsp";

    }


    public String myOrder(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

//        获取 当前登录的用户名
        String username = (String) request.getSession().getAttribute("username");


//        获取 当前登录用户的所有  订单
        String uid = orderService.nameToUid(username);

        List<Orders> orderses = orderService.toUid(uid);

        List<CountAndBooks> bookShow = new LinkedList();

        for (Orders o : orderses) {

            List<CountAndBooks> list = orderService.toOid(o.getOid());

            for (CountAndBooks l : list) {

                bookShow.add(l);

            }

        }

        request.setAttribute("orders", orderses);

        request.setAttribute("bookShow", bookShow);

        return "f:/jsps/order/list.jsp";

    }


    public String giveMoney(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

//        获取订单号
        String oid = request.getParameter("oid");

//        通过 oid 获取 oid内容
        Orders orders = orderService.oidToThis(oid);

        List<CountAndBooks> bookShow = new LinkedList();

        List<CountAndBooks> list = orderService.toOid(oid);

        for (CountAndBooks l : list) {

            bookShow.add(l);

        }

        request.setAttribute("orders", orders);

        request.setAttribute("bookShow", bookShow);

        return "f:/jsps/order/desc.jsp";

    }


    //        支付成功
    public String useMoney(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

//        获取订单号
        String oid = request.getParameter("oid");

        String address = request.getParameter("address");


        orderService.useMoney(oid, address);

//        通过 oid 获取 username
        User user = userService.uidToAll(orderService.oidToThis(oid).getUid());

        new Thread(() -> sendMail(user.getEmail(), user.getUsername())).start();

//        userService.usernameToUid(orderService.oidToThis(oid).getUid());

        request.setAttribute("msg", "感谢你的购买 0w0");
        return "f:/jsps/order/msg.jsp";

    }

    //    发送邮件给购买用户
    private void sendMail(String email, String username) {
        try {
            Session session = MailUtils.createSession("smtp.163.com", "15842209819@163.com", "xxl101354@");
            Mail mail = new Mail("15842209819@163.com", email, "BookStore Support",

                    "<div style=\"background: #17212e\">" + "<div style=\"padding-top: 32px\"></div>" +
                            "<div style=\"font-family: Helvetica, Arial, sans-serif;font-size: 24px;color: #66c0f4;font-weight: bold;\">" +
                            "Hello  " + username + "</div>" +
                            "<div style=\"color: #c6d4df; font-size: 15px\">" + "Thank you for your recent transaction on BookStore." + "</div>" +
                            "<div style=\"color: #c6d4df; font-size: 15px\">" + "The items below have been added to your BookStore Library.\n" + "</div>" +
                            "<div style=\"color: #c6d4df; font-size: 15px\">" + "If you are new to BookStore, you can get the free BookStore application " +
                            "<a href='http://localhost:8080'>here.</a>" + "</div>" + "<div style=\"padding-bottom: 50px\"></div>" + "</div>");

            MailUtils.send(session, mail);
        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public String end(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

//        获取订单号
        String oid = request.getParameter("oid");

        System.out.println(oid);

//        通过 oid 获取 oid内容
        orderService.end(oid);

        request.setAttribute("msg", "请为我们的服务打分 0w0");
        return "f:/jsps/order/msg.jsp";

    }


}


