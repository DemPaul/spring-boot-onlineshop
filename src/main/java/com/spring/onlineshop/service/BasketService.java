package com.spring.onlineshop.service;

import com.spring.onlineshop.model.Basket;
import com.spring.onlineshop.model.Product;
import com.spring.onlineshop.model.User;

import java.util.Optional;

public interface BasketService {

    void addBasket(Basket basket);

    Optional<Basket> getLatestBasketOfUser(User user);

    void addProductToBasket(Product product, Basket basket);

    void lockBasket(Basket basket);
}
