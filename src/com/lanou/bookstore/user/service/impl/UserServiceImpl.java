package com.lanou.bookstore.user.service.impl;

import com.lanou.bookstore.user.dao.impl.UserDaoImpl;
import com.lanou.bookstore.user.domain.User;
import com.lanou.bookstore.user.service.UserService;

/**
 * Created by dllo on 17/9/21.
 */
public class UserServiceImpl implements UserService{
    private UserDaoImpl userDao = new UserDaoImpl();

    @Override
    public void add(User user) {
        userDao.add(user);
    }

    @Override
    public void activated(String code) {
        userDao.activated(code);
    }

    @Override
    public String uidToUsername(String username) {
        return userDao.uidToUsername(username);
    }

    public String usernameToUid(String uid) {
        return userDao.usernameToUid(uid);
    }

    public User uidToAll(String uid) {
        return userDao.uidToAll(uid);
    }
}
