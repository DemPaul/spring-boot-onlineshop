package com.spring.onlineshop.service.impl;

import com.spring.onlineshop.model.Code;
import com.spring.onlineshop.repository.CodeJpaRepository;
import com.spring.onlineshop.service.CodeService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class CodeServiceImpl implements CodeService {

    private static final Logger logger = Logger.getLogger(CodeServiceImpl.class);

    private final CodeJpaRepository codeJpaRepository;

    @Autowired
    public CodeServiceImpl(CodeJpaRepository codeJpaRepository) {
        this.codeJpaRepository = codeJpaRepository;
    }

    @Transactional
    @Override
    public void addCode(Code code) {
        try {
            codeJpaRepository.saveAndFlush(code);
            logger.info("Code " + code + " added to the DataBase");
        } catch (Exception e) {
            logger.error("Problem in working with the DataBase, " +
                    "Code " + code + " isn't added to the DataBase", e);
        }
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Code> getLatestCodeOfEmail(String email) {
        try {
            return codeJpaRepository.findFirstByEmailOrderByIdDesc(email);
        } catch (Exception e) {
            logger.error("Problem in working with the DataBase", e);
        }
        return Optional.empty();
    }
}
