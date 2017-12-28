package com.lanou.bookstore.order.service;

import com.lanou.bookstore.CartItem.Cartltem;
import com.lanou.bookstore.order.dao.OrderDao;
import com.lanou.bookstore.order.domain.CountAndBooks;
import com.lanou.bookstore.order.domain.Orderitem;
import com.lanou.bookstore.order.domain.Orders;
import com.lanou.bookstore.user.domain.User;

import java.util.List;
import java.util.Map;

/**
 * Created by dllo on 17/9/22.
 */
public class OrderService {

    private OrderDao orderDao = new OrderDao();

    public void add(Orders orders) {
        orderDao.add(orders);
    }

    public void orderitemAdd(Orderitem orderitem) {
        orderDao.orderitemAdd(orderitem);
    }

    public List<Orders> toUid(String uid) {
        return orderDao.toUid(uid);
    }

    public List<CountAndBooks> toOid(String oid) {
        return orderDao.toOid(oid);
    }

    public List<Orders> toAllOid() {
        return orderDao.toAllOid();
    }

    public String nameToUid(String username) {
        return orderDao.nameToUid(username);
    }

    public Orders oidToThis(String oid){
        return orderDao.oidToThis(oid);
    }

    public void end(String oid){
        orderDao.end(oid);
    }

    public void useMoney(String oid, String address) {
        orderDao.useMoney(oid, address);
    }
}
