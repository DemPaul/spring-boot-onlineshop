package com.spring.onlineshop.repository;

import com.spring.onlineshop.model.Code;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CodeJpaRepository extends JpaRepository<Code, Long> {

    Optional<Code> findFirstByEmailOrderByIdDesc(String email);
}
