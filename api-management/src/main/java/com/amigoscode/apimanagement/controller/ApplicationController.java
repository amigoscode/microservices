package com.amigoscode.apimanagement.controller;

import com.amigoscode.apimanagement.services.ApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Ali Bouali
 */

@RestController
@RequestMapping("/api/v1/api-management/applications")
@RequiredArgsConstructor
public class ApplicationController {

  private final ApplicationService service;

  @PostMapping("/assign-to-api-key/{app-name}/{api-key}")
  public ResponseEntity<String> assignApplicationToApiKey(
      @PathVariable("app-name") String appName,
      @PathVariable("api-key") String apiKey
  ) {
    return ResponseEntity.ok(service.assignToApiKey(appName, apiKey));
  }

  @DeleteMapping("/revoke/{app-name}/{api-key}")
  public ResponseEntity<String> revokeApplication(
      @PathVariable("app-name") String appName,
      @PathVariable("api-key") String apiKey
  ) {
    return ResponseEntity.ok(service.revokeApplication(appName, apiKey));
  }
}
