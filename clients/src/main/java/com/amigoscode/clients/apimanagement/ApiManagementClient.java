package com.amigoscode.clients.apimanagement;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author Ali Bouali
 */
@FeignClient(
    name = "api-management",
    url = "${clients.api-management.url}"
)
public interface ApiManagementClient {

  @GetMapping("/api/v1/api-keys/{api-key}/applications/{app-name}/authorisations")
   ApiManagementResponse isKeyAuthorizedForApplication(
      @PathVariable("api-key") String apiKey,
      @PathVariable("app-name") String appName
  );
}
