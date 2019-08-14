package com.spring.onlineshop.repository;

import com.spring.onlineshop.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductJpaRepository extends JpaRepository<Product, Long> {

    @Override
    @Query("FROM Product WHERE available = TRUE")
    List<Product> findAll();

    @Modifying
    @Query("UPDATE Product p SET p.name = ?1, p.description = ?2, p.price = ?3 WHERE p.id = ?4")
    void updateProductById(String name, String description, Double price, Long productId);

    @Override
    @Modifying
    @Query("UPDATE Product p SET p.available = FALSE WHERE p = ?1")
    void delete(Product product);
}
