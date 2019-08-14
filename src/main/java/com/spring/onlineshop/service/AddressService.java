package com.spring.onlineshop.service;

import com.spring.onlineshop.model.Address;
import com.spring.onlineshop.model.User;

import java.util.Optional;

public interface AddressService {

    void addAddress(Address address);

    Optional<Address> getLatestAddressOfUser(User user);
}
