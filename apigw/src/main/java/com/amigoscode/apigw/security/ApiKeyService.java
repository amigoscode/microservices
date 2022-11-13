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
@Service("apiKeyService")
public class ApiKeyService implements ApiService {

  @Getter
  private final Map<String, List<String>> apiKeys = new HashMap<>();

  @Override
  public void initialize() {
    // Customer API
    apiKeys.put(
        "VALID-API-KEY", List.of("CUSTOMER-API")
    );
  }

  @Override
  public boolean isNotAuthorized(final String apiKey, final String service) {
    return !isAuthorized(apiKey, service);
  }

  @Override
  public boolean isAuthorized(final String apiKey, final String service) {
    final var services = this.apiKeys.get(apiKey);
    if (isEmpty(services)) {
      return false;
    }
    return services.contains(service);
  }

}
