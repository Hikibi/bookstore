package com.lanou.bookstore.order.domain;

/**
 * Created by dllo on 17/9/22.
 */
public class Orderitem {

    private String iid;
    private int count;
    private double subtotal;
    private String oid;
    private String bid;

    @Override
    public String toString() {
        return "Orderitem{" +
                "iid='" + iid + '\'' +
                ", count=" + count +
                ", subtotal=" + subtotal +
                ", oid='" + oid + '\'' +
                ", bid='" + bid + '\'' +
                '}';
    }

    public Orderitem(String iid, int count, double subtotal, String oid, String bid) {
        this.iid = iid;
        this.count = count;
        this.subtotal = subtotal;
        this.oid = oid;
        this.bid = bid;
    }

    public Orderitem() {
    }

    public String getIid() {
        return iid;
    }

    public void setIid(String iid) {
        this.iid = iid;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    public String getBid() {
        return bid;
    }

    public void setBid(String bid) {
        this.bid = bid;
    }
}
