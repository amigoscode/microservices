package com.amigoscode.apigw.secutity;

import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;

/**
 * @author Ali Bouali
 */
@Service
public class ApiKeyService implements ApiService {

  private static final Map<String, List<String>> API_KEYS =
      Map.of("VALID-API-KEY", List.of("CUSTOMER-API"));

  @Override
  public boolean isAuthorized(String apiKey, String serviceName) {
    return API_KEYS.getOrDefault(apiKey, List.of())
        .stream()
        .anyMatch( s -> s.contains(serviceName));
  }
}
