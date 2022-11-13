package com.amigoscode.apigw.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author Ali Bouali
 */
@FeignClient(
    name = "api-management",
    url = "${api.management.base-url}"
)
public interface ApiManagementClient {

  @GetMapping("/api/v1/api-management/{api-key}/{app-name}")
  ApiManagementResponse isAuthorized(
      @PathVariable("api-key") String apiKey,
      @PathVariable("app-name") String appName
  );
}
