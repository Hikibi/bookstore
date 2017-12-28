package com.lanou.bookstore.user.service;

import com.lanou.bookstore.user.domain.User;

/**
 * Created by dllo on 17/9/21.
 */
public interface UserService {

    void add(User user);

    void activated(String code);


    String uidToUsername(String username);


}
