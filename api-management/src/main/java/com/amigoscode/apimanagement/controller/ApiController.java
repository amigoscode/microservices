package com.amigoscode.apimanagement.controller;

import com.amigoscode.apimanagement.dto.ApiDto;
import com.amigoscode.apimanagement.services.ApiService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Ali Bouali
 */
@RestController
@RequestMapping("/api/v1/api-management")
@RequiredArgsConstructor
public class ApiController {

  private final ApiService service;

  @PostMapping
  public ResponseEntity<String> save(
      @RequestBody ApiDto dto
  ) {
    return ResponseEntity.ok(service.save(dto));
  }

  @DeleteMapping("/{api-key}")
  public ResponseEntity<String> revoke(@PathVariable("api-key") String apiKey) {
    return ResponseEntity.ok(service.revokeApi(apiKey));
  }

  @GetMapping("/{api-key}/{app-name}")
  public ResponseEntity<Boolean> isAuthorized(
      @PathVariable("api-key") String apiKey,
      @PathVariable("app-name") String appName
  ) {
    return ResponseEntity.ok(service.isAuthorized(apiKey, appName));
  }

}
