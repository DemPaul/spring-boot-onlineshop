package com.spring.onlineshop.repository;

import com.spring.onlineshop.model.Address;
import com.spring.onlineshop.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AddressJpaRepository extends JpaRepository<Address, Long> {

    Optional<Address> findFirstByUserOrderByIdDesc(User user);
}
