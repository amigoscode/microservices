package com.amigoscode.apigw.secutity;

/**
 * @author Ali Bouali
 */
public interface ApiService {

  boolean isAuthorized(String apiKey, String serviceName);

  default boolean isNotAuthorized(String apiKey, String serviceName) {
    return !isAuthorized(apiKey, serviceName);
  }

}
