package com.spring.onlineshop.handler;

import com.spring.onlineshop.model.User;
import org.apache.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CustomLogoutSuccessHandler implements LogoutSuccessHandler {

    private static final Logger logger = Logger.getLogger(CustomLogoutSuccessHandler.class);

    @Override
    public void onLogoutSuccess(HttpServletRequest request,
                                HttpServletResponse response,
                                Authentication authentication)
            throws IOException {

        User user = (User) authentication.getPrincipal();
        logger.info("User with email: " + user.getEmail() +
                " successfully logged out");
        request.getSession().setAttribute("message", "You are successfully logged out!");
        response.sendRedirect("/login");
    }
}
