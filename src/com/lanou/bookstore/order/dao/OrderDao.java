package com.lanou.bookstore.order.dao;

import com.lanou.bookstore.CartItem.Cartltem;
import com.lanou.bookstore.order.domain.CountAndBooks;
import com.lanou.bookstore.order.domain.Orderitem;
import com.lanou.bookstore.order.domain.Orders;
import com.lanou.bookstore.user.domain.User;
import com.lanou.jdbc.GxQueryRunner;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * Created by dllo on 17/9/22.
 */
public class OrderDao {

    private QueryRunner qr = new GxQueryRunner();

    //    添加订单
    public void add(Orders orders) {
        try {
            String sql = "INSERT INTO orders VALUES(?,?,?,?,?,?)";
            Object[] params = {orders.getOid(), orders.getOrdertime(), orders.getTotal(),
                    orders.getState(), orders.getUid(), orders.getAddress()};
            qr.update(sql, params);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void orderitemAdd(Orderitem orderitem) {
        try {
            String sql = "INSERT INTO orderitem VALUES(?,?,?,?,?)";
            Object[] params = {orderitem.getIid(), orderitem.getCount(), orderitem.getSubtotal(),
                    orderitem.getOid(), orderitem.getBid()};
            qr.update(sql, params);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Orders> toUid(String uid) {
        try {
            String sql = "SELECT * FROM orders WHERE uid=? ORDER BY ordertime DESC";

            return qr.query(sql, new BeanListHandler<>(Orders.class), uid);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<CountAndBooks> toOid(String oid) {
        try {
            String sql = "SELECT item.oid, item.count, book.* FROM orderitem item, book book WHERE item.oid=? AND item.bid = book.bid";

//            List<CountAndBooks> query = qr.query(sql, new BeanListHandler<>(CountAndBooks.class), oid);
//            System.out.println(query);

            return qr.query(sql, new BeanListHandler<>(CountAndBooks.class), oid);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;

    }

    public int oidToState(String oid) {
        try {
            String sql = "SELECT state FROM orders WHERE oid=?";

            return qr.query(sql, new ScalarHandler<>(), oid);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }


    public List<Orders> toAllOid() {
        try {
            String sql = "SELECT * FROM orders";

            return qr.query(sql, new BeanListHandler<>(Orders.class));

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    //    通过 username 获取 uid
    public String nameToUid(String username) {
        try {
            String sql = "SELECT uid FROM tb_user WHERE username=?";

            return qr.query(sql, new ScalarHandler<>(), username);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    //    通过 oid 获取 这条 oid 的数据
    public Orders oidToThis(String oid) {
        try {
            String sql = "SELECT * FROM orders WHERE oid=?";

            return qr.query(sql, new BeanHandler<>(Orders.class), oid);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

//    通过 oid 修改 state 为4    (代表订单已结束)
    public void end(String oid) {
        try {

            String sql = "UPDATE orders SET state=4 WHERE oid=?";

            qr.update(sql, oid);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

//    通过 oid 修改 state 为2   (代表订单以付款)
    public void useMoney(String oid, String address) {
        try {

            String sql = "UPDATE orders SET state=2, address=? WHERE oid=?";

            Object[] params = {address, oid};

            qr.update(sql, params);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
