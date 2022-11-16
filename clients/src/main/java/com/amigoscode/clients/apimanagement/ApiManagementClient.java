package com.amigoscode.clients.apimanagement;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
        name = "api-management",
        url = "${clients.api-management.url}"
)
public interface ApiManagementClient {

    @GetMapping("/api/v1/api-keys/{apiKey}/applications/{appName}/authorisations")
    ApiManagementResponse isKeyAuthorizedForApplication(
            @PathVariable("apiKey") String apiKey,
            @PathVariable("appName") String appName
    );
}
