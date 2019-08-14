package com.spring.onlineshop.service.impl;

import com.spring.onlineshop.model.Basket;
import com.spring.onlineshop.model.Product;
import com.spring.onlineshop.model.User;
import com.spring.onlineshop.repository.BasketJpaRepository;
import com.spring.onlineshop.service.BasketService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class BasketServiceImpl implements BasketService {

    private static final Logger logger = Logger.getLogger(BasketServiceImpl.class);

    private final BasketJpaRepository basketJpaRepository;

    @Autowired
    public BasketServiceImpl(BasketJpaRepository basketJpaRepository) {
        this.basketJpaRepository = basketJpaRepository;
    }

    @Transactional
    @Override
    public void addBasket(Basket basket) {
        try {
            basketJpaRepository.save(basket);
            logger.info("Basket " + basket + " added to the DataBase");
        } catch (Exception e) {
            logger.error("Problem in working with the DataBase, " +
                    "Basket " + basket + " isn't added to the DataBase", e);
        }
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Basket> getLatestBasketOfUser(User user) {
        try {
            return basketJpaRepository.findLatestBasketOfUser(user).get(0);
        } catch (IndexOutOfBoundsException e) {
            logger.error("Problem in working with the DataBase");
        } catch (Exception e) {
            logger.error("Problem in working with the DataBase", e);
        }
        return Optional.empty();
    }

    @Transactional
    @Override
    public void addProductToBasket(Product product, Basket basket) {
        try {
            basketJpaRepository.addProductToBasket(product.getId(), basket.getId());
            logger.info("Product (id=" + product.getId() +
                    ") is added to basket (id=" + basket.getId() +
                    ") by the user (id=" + basket.getUser().getId() + ")");
        } catch (Exception e) {
            logger.error("Problem in working with the DataBase, Product (id=" + product.getId() +
                    ") isn't added to the Basket (id=" + basket.getId() + ") in the DataBase");
        }
    }

    @Transactional
    @Override
    public void lockBasket(Basket basket) {
        try {
            basket.setLocked(true);
            basketJpaRepository.lockBasket(basket.isLocked(), basket);
            logger.info("Basket " + basket.toString() + " is locked " +
                    "by the user (id=" + basket.getUser().getId() + ")");
        } catch (Exception e) {
            logger.error("Problem in working with the DataBase, Basket "
                    + basket + " isn't locked", e);
        }
    }
}
