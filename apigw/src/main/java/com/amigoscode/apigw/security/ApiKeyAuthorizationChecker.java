package com.amigoscode.apigw.security;

public interface ApiKeyAuthorizationChecker {
    boolean isAuthorized(String apiKey, String applicationName);
}
