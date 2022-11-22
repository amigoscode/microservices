package com.amigoscode.apimanagement.apikey;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Ali Bouali
 */
@RestController
@RequestMapping("/api/v1/api-keys")
@RequiredArgsConstructor
public class ApiKeyController {

  private final ApiKeyService service;

  @PostMapping
  public ResponseEntity<String> generateNewKey(
      @RequestBody ApiKeyRequest request
  ) {
    return ResponseEntity.ok(service.save(request));
  }

  @PutMapping("/{api-key}/revoke")
  public ResponseEntity<String> revokeApiKey(
      @PathVariable("api-key") String apiKey
  ) {
    return ResponseEntity.ok(service.revokeApiKey(apiKey));
  }

  @GetMapping("/{api-key}/applications/{app-name}/authorisations")
  public ResponseEntity<Boolean> isKeyAuthorizedForApplication(
      @PathVariable("api-key") String apiKey,
      @PathVariable("app-name") String appName
  ) {
    return ResponseEntity.ok(service.isAuthorized(apiKey, appName));
  }
}
