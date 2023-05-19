package com.thiago.bank.utils;

import java.util.Random;

public class BankAccountNumberGenerator {
    
    public static String generate() {
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            sb.append(random.nextInt(10));
        }
        return sb.toString();
    }

}
