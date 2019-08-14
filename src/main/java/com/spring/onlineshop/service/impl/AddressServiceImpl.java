package com.spring.onlineshop.service.impl;

import com.spring.onlineshop.repository.AddressJpaRepository;
import com.spring.onlineshop.model.Address;
import com.spring.onlineshop.model.User;
import com.spring.onlineshop.service.AddressService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class AddressServiceImpl implements AddressService {

    private static final Logger logger = Logger.getLogger(AddressServiceImpl.class);

    private final AddressJpaRepository addressJpaRepository;

    @Autowired
    public AddressServiceImpl(AddressJpaRepository addressJpaRepository) {
        this.addressJpaRepository = addressJpaRepository;
    }

    @Transactional
    @Override
    public void addAddress(Address address) {
        try {
            addressJpaRepository.save(address);
            logger.info("Address " + address + " added to the DataBase");
        } catch (Exception e) {
            logger.error("Problem in working with the DataBase, " +
                    "Address " + address + " isn't added to the DataBase", e);
        }
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Address> getLatestAddressOfUser(User user) {
        try {
            return addressJpaRepository.findFirstByUserOrderByIdDesc(user);
        } catch (Exception e) {
            logger.error("Problem in working with the DataBase", e);
        }
        return Optional.empty();
    }
}
