package com.spring.onlineshop.repository;

import com.spring.onlineshop.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserJpaRepository extends JpaRepository<User, Long> {

    boolean existsUsersByEmail(String email);

    Optional<User> findUserByEmail(String email);

    @Modifying
    @Query("UPDATE User u SET u.email = ?1, u.password = ?2, u.role = ?3 WHERE u.id = ?4")
    void updateUserById(String email, String password, String role, Long userId);
}
