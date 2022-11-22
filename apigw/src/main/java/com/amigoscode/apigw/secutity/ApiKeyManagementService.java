package com.amigoscode.apigw.secutity;

import com.amigoscode.clients.apimanagement.ApiManagementClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author Ali Bouali
 */
@Service("api-management-service")
@RequiredArgsConstructor
public class ApiKeyManagementService implements ApiService {

  private final ApiManagementClient client;

  @Override
  public boolean isAuthorized(String apiKey, String serviceName) {
    return client.isKeyAuthorizedForApplication(apiKey, serviceName)
        .isAuthorized();
  }
}
