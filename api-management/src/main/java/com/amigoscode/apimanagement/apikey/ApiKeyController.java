package com.amigoscode.apimanagement.apikey;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/api-keys")
@RequiredArgsConstructor
public class ApiKeyController {

  private final ApiKeyService apiKeyService;

  @PostMapping
  public ResponseEntity<String> generateNewKey(
          @RequestBody NewApiKeyRequest apiKeyRequest) {
    return ResponseEntity.ok(apiKeyService.save(apiKeyRequest));
  }

  @PutMapping("{apiKey}/revoke")
  public void revokeKey(@PathVariable("apiKey") String apiKey) {
    apiKeyService.revokeApi(apiKey);
  }

  @GetMapping("{apiKey}/applications/{appName}/authorisations")
  public ResponseEntity<Boolean> isKeyAuthorizedForApplication(
          @PathVariable("apiKey") String apiKey,
          @PathVariable("appName") String appName) {
    return ResponseEntity.ok(apiKeyService.isAuthorized(apiKey, appName));
  }

}
