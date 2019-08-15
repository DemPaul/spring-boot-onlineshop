package com.spring.onlineshop.utils;

import java.util.Random;

public class ConfirmCodeGenerator {

    public static String generateCode() {
        return String.format("%04d", new Random().nextInt(10000));
    }
}
