package com.thiago.bank.utils;

import java.util.UUID;

public class PixKeyGenerator {
    
    public static String generate() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString().replace("-", "");
    }
}
