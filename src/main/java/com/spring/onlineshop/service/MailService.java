package com.spring.onlineshop.service;

import com.spring.onlineshop.model.Code;

public interface MailService {

    void sendConfirmCode(Code code);
}
