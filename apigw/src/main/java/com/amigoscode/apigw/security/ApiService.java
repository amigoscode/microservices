package com.amigoscode.apigw.security;

/**
 * @author Ali Bouali
 */
public interface ApiService {

  void initialize();

  boolean  isAuthorized(final String apiKey, final String service);

  boolean isNotAuthorized(final String apiKey, final String service);

}
