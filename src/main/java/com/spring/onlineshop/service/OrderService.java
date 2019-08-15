package com.spring.onlineshop.service;

import com.spring.onlineshop.model.Order;
import com.spring.onlineshop.model.User;

import java.util.Optional;

public interface OrderService {

    void addOrder(Order order);

    Optional<Order> getLatestOrderOfUser(User user);

    void confirmOrder(Order order);
}
