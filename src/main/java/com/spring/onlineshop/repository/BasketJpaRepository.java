package com.spring.onlineshop.repository;

import com.spring.onlineshop.model.Basket;
import com.spring.onlineshop.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BasketJpaRepository extends JpaRepository<Basket, Long> {

    Optional<Basket> findFirstByUserAndLockedIsFalseOrderByIdDesc(User user);
}
