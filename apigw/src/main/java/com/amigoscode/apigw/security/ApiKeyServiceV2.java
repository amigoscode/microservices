package com.amigoscode.apigw.security;

import static org.springframework.util.CollectionUtils.isEmpty;

import com.amigoscode.apigw.clients.ApiManagementClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author Ali Bouali
 */
@Service("apiKeyServiceV2")
@RequiredArgsConstructor
public class ApiKeyServiceV2 implements ApiService {


  private final ApiManagementClient apiManagementClient;

  @Override
  public boolean isNotAuthorized(final String apiKey, final String service) {
    return !isAuthorized(apiKey, service);
  }

  @Override
  public void initialize() {
  }

  @Override
  public boolean isAuthorized(final String apiKey, final String service) {
    return apiManagementClient.isAuthorized(apiKey, service).isAuthorized();
  }
}
