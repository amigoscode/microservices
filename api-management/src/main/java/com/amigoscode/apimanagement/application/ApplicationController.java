package com.amigoscode.apimanagement.application;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Ali Bouali
 */
@RestController
@RequestMapping("/api/v1/applications")
@RequiredArgsConstructor
public class ApplicationController {

  private final ApplicationService service;

  @PostMapping("/{app-name}/authorise/{api-key}")
  public ResponseEntity<String> assignApplicationToApiKey(
      @PathVariable("app-name") String appName,
      @PathVariable("api-key") String  apiKey
  ) {
    return ResponseEntity.ok(service.assignToApiKey(appName, apiKey));
  }

  @PutMapping("/{app-name}/revoke/{api-key}")
  public void revokeApplication(
      @PathVariable("app-name") String appName,
      @PathVariable("api-key") String  apiKey
  ) {
    service.revokeApplication(appName, apiKey);
  }
}
