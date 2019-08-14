package com.spring.onlineshop.service.impl;

import com.spring.onlineshop.model.Order;
import com.spring.onlineshop.model.User;
import com.spring.onlineshop.repository.OrderJpaRepository;
import com.spring.onlineshop.service.OrderService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    private static final Logger logger = Logger.getLogger(OrderServiceImpl.class);

    private final OrderJpaRepository orderJpaRepository;

    @Autowired
    public OrderServiceImpl(OrderJpaRepository orderJpaRepository) {
        this.orderJpaRepository = orderJpaRepository;
    }

    @Transactional
    @Override
    public void addOrder(Order order) {
        try {
            orderJpaRepository.save(order);
            logger.info("Order " + order + " added to the DataBase");
        } catch (Exception e) {
            logger.error("Problem in working with the DataBase, " +
                    "Order " + order + " isn't added to the DataBase", e);
        }
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Order> getLatestOrderOfUser(User user) {
        try {
            return orderJpaRepository.getLatestOrderOfUser(user).get(0);
        } catch (Exception e) {
            logger.error("Problem in working with the DataBase", e);
        }
        return Optional.empty();
    }

    @Transactional
    @Override
    public void confirmOrder(Order order) {
        try {
            orderJpaRepository.confirmOrder(order);
            logger.info("Order " + order.toString() + " is confirmed " +
                    "by the user (id=" + order.getBasket().getUser().getId() + ")");
        } catch (Exception e) {
            logger.error("Problem in working with the DataBase, Order "
                    + order + " isn't confirmed", e);
        }
    }
}
