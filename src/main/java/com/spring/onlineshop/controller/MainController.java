package com.spring.onlineshop.controller;

import com.spring.onlineshop.model.Product;
import com.spring.onlineshop.model.User;
import com.spring.onlineshop.service.ProductService;
import com.spring.onlineshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.PostConstruct;
import java.util.Objects;

@Controller
public class MainController {

    private UserService userService;
    private ProductService productService;

    @Autowired
    public MainController(UserService userService, ProductService productService) {
        this.userService = userService;
        this.productService = productService;
    }

    @RequestMapping(path = {"/"}, method = RequestMethod.GET)
    public String index(@AuthenticationPrincipal User user) {
        String role = user.getRole();
        if (role.equals("ROLE_ADMIN")) {
            return "redirect:/admin/user/all";
        } else if (role.equals("ROLE_USER")) {
            return "redirect:/user/product/all";
        } else {
            return "index";
        }
    }

    @RequestMapping(path = {"/registration"}, method = RequestMethod.GET)
    public String registrationGet() {
        return "registration";
    }

    @RequestMapping(path = {"/registration"}, method = RequestMethod.POST)
    public String registrationPost(
            @RequestBody MultiValueMap<String, String> formData, Model model) {
        String email = formData.getFirst("email");
        String password = formData.getFirst("password");
        String repeatPassword = formData.getFirst("repeatPassword");
        if (isNullOrEmpty(email) || isNullOrEmpty(password) || isNullOrEmpty(repeatPassword)) {
            model.addAttribute("incompleteFormError",
                    "The form is not fully completed!");
            model.addAttribute("lastEnteredEmail", email);
            model.addAttribute("lastEnteredPassword", password);
            model.addAttribute("lastEnteredRepeatPassword", repeatPassword);
            return "registration";
        } else {
            if (Objects.equals(password, repeatPassword)) {
                User user = new User(email, password, "ROLE_USER");
                if (!userService.isPresent(email)) {
                    userService.addUser(user);
                    return "redirect:/login";
                } else {
                    model.addAttribute("userIsAlreadyPresentError",
                            "The user with such email already exists!");
                    model.addAttribute("lastEnteredEmail", email);
                    model.addAttribute("lastEnteredPassword", password);
                    model.addAttribute("lastEnteredRepeatPassword", repeatPassword);
                    return "registration";
                }
            } else {
                model.addAttribute("passwordEquivalenceError",
                        "The entered passwords are not identical!");
                model.addAttribute("lastEnteredEmail", email);
                model.addAttribute("lastEnteredPassword", password);
                return "registration";
            }
        }
    }

    @PostConstruct
    public void init() {
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

    private boolean isNullOrEmpty(String requestParameter) {
        return Objects.isNull(requestParameter) || requestParameter.trim().isEmpty();
    }
}
