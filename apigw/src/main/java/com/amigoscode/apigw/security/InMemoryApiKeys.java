package com.amigoscode.apigw.security;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("inMemory")
public class InMemoryApiKeys implements ApiKeyAuthorizationChecker {
    private static final Map<String, List<String>> apiKeys = Map.of(
            "VALID-API-KEY", List.of("CUSTOMER-API")
    );

    @Override
    public boolean isAuthorized(String apiKey, String application) {
        return apiKeys.getOrDefault(apiKey, List.of())
                .stream()
                .anyMatch(s -> s.contains(application));
    }
}
