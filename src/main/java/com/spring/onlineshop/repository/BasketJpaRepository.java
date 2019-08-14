package com.spring.onlineshop.repository;

import com.spring.onlineshop.model.Basket;
import com.spring.onlineshop.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BasketJpaRepository extends JpaRepository<Basket, Long> {

    @Query("FROM Basket WHERE user = ?1 AND locked = FALSE ORDER BY id DESC")
    List<Optional<Basket>> findLatestBasketOfUser(User user);

    @Modifying
    @Query(nativeQuery = true,
            value = "INSERT INTO product_basket(product_id, basket_id) VALUES (?1, ?2)")
    void addProductToBasket(Long productId, Long basketId);

    @Modifying
    @Query("UPDATE Basket b SET b.locked = ?1 WHERE b = ?2")
    void lockBasket(Boolean locked, Basket basket);
}
