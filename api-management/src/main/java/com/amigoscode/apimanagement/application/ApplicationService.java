package com.amigoscode.apimanagement.application;

import com.amigoscode.apimanagement.apikey.ApiKeyRepository;

import javax.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ApplicationService {

  private final ApplicationRepository repository;
  private final ApiKeyRepository apiKeyRepository;

  public void assignToApiKey(String appName, String apiKey) {
    var key = apiKeyRepository.findByKey(apiKey)
        .orElseThrow(() -> new EntityNotFoundException("No api was found"));

    Application application = Application.builder()
        .name(appName)
        // todo: better implement an approval system
        .enabled(true)
        .revoked(false)
        .approved(true)
        .apiKey(key)
        .build();

    repository.save(application);
  }

  public void revokeApplication(String appName, String apiKey) {
    if (!apiKeyRepository.doesKeyExists(apiKey)) {
      throw new  EntityNotFoundException("No api key was found");
    }

    var application = repository.findByName(appName)
        .orElseThrow(() -> new EntityNotFoundException("No App was found"));

    application.setRevoked(true);
    application.setApproved(false);
    application.setEnabled(false);
    repository.save(application);
  }
}
