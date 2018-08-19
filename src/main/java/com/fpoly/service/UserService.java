package com.fpoly.service;

import com.fpoly.model.User;

public interface UserService {

    void save(User user);

    boolean existUsername(String username);

    User findByUsername(String username);

    User findById(Long userPrincipalId);
}