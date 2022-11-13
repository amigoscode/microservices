package com.amigoscode.apimanagement.services;

import com.amigoscode.apimanagement.models.Application;
import com.amigoscode.apimanagement.repositories.ApiRepository;
import com.amigoscode.apimanagement.repositories.ApplicationRepository;
import javax.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author Ali Bouali
 */
@Service
@RequiredArgsConstructor
public class ApplicationService {

  private final ApplicationRepository repository;
  private final ApiRepository apiRepository;

  public String assignToApiKey(String appName, String apiKey) {
    final var api = apiRepository.findByKey(apiKey)
        .orElseThrow(() -> new EntityNotFoundException("No api was found"));
    final Application application = Application.builder()
        .name(appName)
        // todo: better implement an approval system
        .enabled(true)
        .revoked(false)
        .approved(true)
        .api(api)
        .build();
    repository.save(application);

    return String.format("Assigned APP : %s to API-KEY %s", appName, apiKey);
  }

  public String revokeApplication(String appName, String apiKey) {
    final var api = apiRepository.findByKey(apiKey)
        .orElseThrow(() -> new EntityNotFoundException("No api was found"));
    final var application = repository.findByName(appName)
        .orElseThrow(() -> new EntityNotFoundException("No App was found"));
    application.setRevoked(true);
    application.setApproved(false);
    application.setEnabled(false);
    repository.save(application);
    return "Application successfully revoked";
  }
}
