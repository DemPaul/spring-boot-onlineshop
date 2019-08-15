package com.spring.onlineshop;

import com.spring.onlineshop.model.Product;
import com.spring.onlineshop.model.User;
import com.spring.onlineshop.service.ProductService;
import com.spring.onlineshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements ApplicationListener<ContextRefreshedEvent> {

    private final UserService userService;
    private final ProductService productService;

    @Autowired
    public DataLoader(UserService userService, ProductService productService) {
        this.userService = userService;
        this.productService = productService;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        User admin = new User("admin@admin", "admin", "ROLE_ADMIN");
        User user = new User("1@1", "1111", "ROLE_USER");
        userService.addUser(admin);
        userService.addUser(user);

        Product product1 = new Product("Bread", "Black bread", 8.99);
        Product product2 = new Product("Butter", "Classic butter", 9.99);
        Product product3 = new Product("Knife", "Very sharp", 15.99);
        productService.addProduct(product1);
        productService.addProduct(product2);
        productService.addProduct(product3);
    }
}
