package com.spring.onlineshop.repository;

import com.spring.onlineshop.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserJpaRepository extends JpaRepository<User, Long> {

    boolean existsUsersByEmail(String email);

    Optional<User> findUserByEmail(String email);
}
