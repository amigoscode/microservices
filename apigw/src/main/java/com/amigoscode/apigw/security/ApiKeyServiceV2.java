package com.amigoscode.apigw.security;

import com.amigoscode.clients.apimanagement.ApiManagementClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service("apiKeyServiceV2")
@RequiredArgsConstructor
public class ApiKeyServiceV2 implements ApiService {

  private final ApiManagementClient apiManagementClient;

  @Override
  public boolean isAuthorized(String apiKey, String service) {
    return apiManagementClient.isKeyAuthorizedForApplication(apiKey, service).isAuthorized();
  }
}
