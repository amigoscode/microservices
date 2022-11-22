package com.amigoscode.apimanagement.application;

import com.amigoscode.apimanagement.apikey.ApiKeyRepository;
import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author Ali Bouali
 */
@Service
@RequiredArgsConstructor
public class ApplicationService {

  private final ApiKeyRepository apiKeyRepository;
  private final ApplicationRepository applicationRepository;

  public String assignToApiKey(String appName, String apiKey) {
    // get the api from the DB or else throw exp
    var api = apiKeyRepository.findByKey(apiKey)
        .orElseThrow(() -> new EntityNotFoundException("No API was found"));

    // create an Application (Entity) object
    final Application application = Application.builder()
        .name(appName)
        .enabled(true)
        .revoked(false)
        .approved(true)
        .apiKey(api)
        .build();
    //save
    applicationRepository.save(application);
    return String.format("Assigned APP : %s to API-KEY : %s", appName, apiKey);
  }

  @Transactional
  public void revokeApplication(String appName, String apiKey) {
    var api = apiKeyRepository.findByKeyAndApplicationName(apiKey, appName)
        .orElseThrow(() -> new EntityNotFoundException("No API match was found"));
    var applicationOptional = api.getApplications()
        .stream()
        .filter(app -> appName.equals(app.getName()))
        .findFirst()
        .or(null);
    if (applicationOptional.isPresent()) {
      var application = applicationOptional.get();
      application.setEnabled(false);
      application.setRevoked(true);
      application.setApproved(false);
      applicationRepository.save(application);
    }
  }

}
