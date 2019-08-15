package com.spring.onlineshop.repository;

import com.spring.onlineshop.model.Order;
import com.spring.onlineshop.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderJpaRepository extends JpaRepository<Order, Long> {

    @Query("FROM Order o WHERE o.basket.user = ?1 AND confirmed = FALSE ORDER BY id DESC")
    List<Order> findLatestOrderByUser(User user);
}
