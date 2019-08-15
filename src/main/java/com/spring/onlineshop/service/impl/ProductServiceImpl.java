package com.spring.onlineshop.service.impl;

import com.spring.onlineshop.repository.ProductJpaRepository;
import com.spring.onlineshop.model.Product;
import com.spring.onlineshop.service.ProductService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private static final Logger logger = Logger.getLogger(ProductServiceImpl.class);

    private final ProductJpaRepository productJpaRepository;

    @Autowired
    public ProductServiceImpl(ProductJpaRepository productJpaRepository) {
        this.productJpaRepository = productJpaRepository;
    }

    @Transactional
    @Override
    public void addProduct(Product product) {
        try {
            productJpaRepository.saveAndFlush(product);
            logger.info("Product " + product + " added to the DataBase");
        } catch (Exception e) {
            logger.error("Problem in working with the DataBase, " +
                    "Product " + product + " isn't added to the DataBase", e);
        }
    }

    @Transactional(readOnly = true)
    @Override
    public List<Product> getAll() {
        try {
            return productJpaRepository.findAllByAvailableIsTrue();
        } catch (Exception e) {
            logger.error("Problem in working with the DataBase", e);
        }
        return Collections.emptyList();
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Product> getProductById(long id) {
        try {
            return productJpaRepository.findById(id);
        } catch (Exception e) {
            logger.error("Problem in working with the DataBase", e);
        }
        return Optional.empty();
    }

    @Transactional
    @Override
    public void changeProduct(Product oldProduct, Product newProduct) {
        try {
            oldProduct.setName(newProduct.getName());
            oldProduct.setDescription(newProduct.getDescription());
            oldProduct.setPrice(newProduct.getPrice());
            productJpaRepository.saveAndFlush(oldProduct);
            logger.info("Product " + oldProduct + " changed in DataBase to " + newProduct);
        } catch (Exception e) {
            logger.error("Problem in working with the DataBase", e);
        }
    }

    @Transactional
    @Override
    public void removeProduct(Product product) {
        try {
            product.setAvailable(false);
            productJpaRepository.saveAndFlush(product);
            logger.info("Product " + product + " become unavailable");
        } catch (Exception e) {
            logger.error("Problem in working with the DataBase, Product "
                    + product + " isn't become unavailable", e);
        }
    }
}
