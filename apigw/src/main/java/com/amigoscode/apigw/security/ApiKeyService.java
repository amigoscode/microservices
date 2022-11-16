package com.amigoscode.apigw.security;

import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;

@Service("apiKeyService")
public class ApiKeyService implements ApiService {

  private static final Map<String, List<String>> apiKeys = Map.of(
          "VALID-API-KEY", List.of("CUSTOMER-API")
  );

  @Override
  public boolean isAuthorized(String apiKey, String service) {
    return apiKeys.getOrDefault(apiKey, List.of())
            .stream()
            .anyMatch(s -> s.contains(service));
  }

}
