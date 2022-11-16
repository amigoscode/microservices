package com.amigoscode.apigw.security;

public interface ApiService {

  boolean  isAuthorized(String apiKey, String application);

  default boolean isNotAuthorized(String apiKey, String application) {
    return !isAuthorized(apiKey, application);
  }

}
