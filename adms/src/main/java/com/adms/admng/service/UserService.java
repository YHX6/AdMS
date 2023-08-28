package com.adms.admng.service;

import com.adms.admng.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> findAll();

    Optional<User> findById(String id);

    User save(User user);

    void deleteById(String id);

    Optional<User> findUserByUsername(String username);

    public boolean findExistByUsername(String username);

}
