package com.spring.onlineshop.repository;

import com.spring.onlineshop.model.Order;
import com.spring.onlineshop.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderJpaRepository extends JpaRepository<Order, Long> {

    @Query("FROM Order o WHERE o.address.user = ?1 AND confirmed = FALSE ORDER BY id DESC")
    List<Optional<Order>> getLatestOrderOfUser(User user);

    @Modifying
    @Query("UPDATE Order o SET o.confirmed = TRUE WHERE o = ?1")
    void confirmOrder(Order order);
}
