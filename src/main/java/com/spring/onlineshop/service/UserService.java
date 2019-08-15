package com.spring.onlineshop.service;

import com.spring.onlineshop.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    void addUser(User user);

    boolean isPresent(String email);

    List<User> getAll();

    Optional<User> getUserById(long id);

    Optional<User> getUserByEmail(String email);

    void changeUser(User oldUser, User newUser);

    void removeUser(User user);
}
