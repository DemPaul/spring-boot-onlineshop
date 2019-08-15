package com.spring.onlineshop.service.impl;

import com.spring.onlineshop.repository.UserJpaRepository;
import com.spring.onlineshop.model.User;
import com.spring.onlineshop.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger logger = Logger.getLogger(UserServiceImpl.class);

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    private final UserJpaRepository userJpaRepository;

    @Autowired
    public UserServiceImpl(UserJpaRepository userJpaRepository) {
        this.userJpaRepository = userJpaRepository;
    }

    @Transactional
    @Override
    public void addUser(User user) {
        try {
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            userJpaRepository.saveAndFlush(user);
            logger.info("User " + user + " added to the DataBase");
        } catch (Exception e) {
            logger.error("Problem in working with the DataBase, " +
                    "User " + user + " isn't added to the DataBase", e);
        }
    }

    @Transactional(readOnly = true)
    @Override
    public boolean isPresent(String email) {
        try {
            return userJpaRepository.existsUsersByEmail(email);
        } catch (Exception e) {
            logger.error("Problem in working with the DataBase", e);
        }
        return false;
    }

    @Transactional(readOnly = true)
    @Override
    public List<User> getAll() {
        try {
            return userJpaRepository.findAll();
        } catch (Exception e) {
            logger.error("Problem in working with the DataBase", e);
        }
        return Collections.emptyList();
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<User> getUserById(long id) {
        try {
            return userJpaRepository.findById(id);
        } catch (Exception e) {
            logger.error("Problem in working with the DataBase", e);
        }
        return Optional.empty();
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<User> getUserByEmail(String email) {
        try {
            return userJpaRepository.findUserByEmail(email);
        } catch (Exception e) {
            logger.error("Problem in working with the DataBase", e);
        }
        return Optional.empty();
    }

    @Transactional
    @Override
    public void changeUser(User oldUser, User newUser) {
        try {
            oldUser.setEmail(newUser.getEmail());
            oldUser.setPassword(bCryptPasswordEncoder.encode(newUser.getPassword()));
            oldUser.setRole(newUser.getRole());
            userJpaRepository.saveAndFlush(oldUser);
            logger.info("User " + oldUser + " changed in DataBase to " + newUser);
        } catch (Exception e) {
            logger.error("Problem in working with the DataBase", e);
        }
    }

    @Transactional
    @Override
    public void removeUser(User user) {
        try {
            userJpaRepository.delete(user);
            logger.info("User " + user + "and all his data removed from DataBase");
        } catch (Exception e) {
            logger.error("Problem in working with the DataBase, User "
                    + user + " isn't removed from DataBase", e);
        }
    }
}
