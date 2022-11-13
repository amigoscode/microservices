package com.amigoscode.apigw.security;

import static org.springframework.util.CollectionUtils.isEmpty;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.Getter;
import org.springframework.stereotype.Service;

/**
 * @author Ali Bouali
 */
@Service
public class ApiKeyService {

  @Getter
  private final Map<String, List<String>> apiKeys = new HashMap<>();

  public void initialize() {
    // Customer API
    apiKeys.put(
        "VALID-API-KEY", List.of("CUSTOMER-API")
    );
  }

  public List<String> getAuthorizedApis(String key) {
    final var services = this.apiKeys.get(key);
    return services == null ? Collections.emptyList() : services;
  }

  public boolean isNotAuthorized(final String apiKey, final String service) {
    return !isAuthorized(apiKey, service);
  }

  public boolean isAuthorized(final String apiKey, final String service) {
    final var services = this.apiKeys.get(apiKey);
    if (isEmpty(services)) {
      return false;
    }
    return services.contains(service);
  }

}
