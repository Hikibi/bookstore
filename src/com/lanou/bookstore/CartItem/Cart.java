package com.lanou.bookstore.CartItem;

import java.util.Map;

/**
 * Created by dllo on 17/9/21.
 */
public class Cart {
    private Map<String, Cartltem> cartMap;

    public Map<String, Cartltem> getCartMap() {
        return cartMap;
    }


    public void setCartMap(Map<String, Cartltem> cartMap) {
        this.cartMap = cartMap;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "cartMap=" + cartMap +
                '}';
    }

    public Cart(Map<String, Cartltem> cartMap) {
        this.cartMap = cartMap;
    }

    public Cart() {
    }


}
