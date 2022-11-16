package com.amigoscode.apimanagement.apikey;

import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class UUIDKeyGenerator implements KeyGenerator {

    @Override
    public String generate() {
        return UUID.randomUUID().toString();
    }

}
