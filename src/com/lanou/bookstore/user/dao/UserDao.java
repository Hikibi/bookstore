package com.lanou.bookstore.user.dao;

import com.lanou.bookstore.user.domain.User;

/**
 * Created by dllo on 17/9/21.
 */
public interface UserDao {

    void add(User user);

    String uidToUsername(String username);

    void activated(String code);

    boolean nameJudge(String username);

    boolean stateJudge(String code);

    boolean namePasswordJudge(String username, String password);

    boolean usernamePasswordStateJudge(String username, String password);
}
