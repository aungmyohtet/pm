package com.aungmyohtet.pm.repository;

import com.aungmyohtet.pm.entity.User;

public interface UserRepository {

    void save(User user);

    User findByEmail(String email);

}
