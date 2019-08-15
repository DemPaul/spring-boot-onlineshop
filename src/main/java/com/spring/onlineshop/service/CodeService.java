package com.spring.onlineshop.service;

import com.spring.onlineshop.model.Code;

import java.util.Optional;

public interface CodeService {

    void addCode(Code code);

    Optional<Code> getLatestCodeOfEmail(String email);
}
