package com.amigoscode.apimanagement.apikey;

import com.amigoscode.apimanagement.application.Application;
import com.amigoscode.apimanagement.application.ApplicationRepository;
import java.time.LocalDateTime;
import java.util.UUID;
import java.util.stream.Collectors;
import javax.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

/**
 * @author Ali Bouali
 */
@Service
@RequiredArgsConstructor
public class ApiKeyService {

  private static final Integer EXPIRATION_DATE_IN_DAYS = 90;
  private final ApiKeyRepository apiKeyRepository;
  private final ApplicationRepository applicationRepository;

  public String save(ApiKeyRequest request) {
    // generate a random / unique key
    var key = generateRandomKey();

    // transform / create an ApiKey (Entity)
    var apiKey = ApiKey.builder()
        .key(key)
        .name(request.getName())
        .description(request.getDescription())
        .expirationDate(LocalDateTime.now().plusDays(EXPIRATION_DATE_IN_DAYS))
        .neverExpires(false)
        // todo implement an approval mechanism
        .approved(true)
        // todo implement a mechanism to enable the API-KEY
        .enabled(true)
        .revoked(false)
        .createdDate(LocalDateTime.now())
        .build();

    // save the ApiKey
    var savedApiKey = apiKeyRepository.save(apiKey);

    // Attach / assign the application to the ApiKey
    if (!CollectionUtils.isEmpty(request.getApplications())) {
      var applications = request.getApplications()
          .stream()
          .distinct()
          .map(app -> Application.builder()
              .name(app)
              .enabled(true)
              .revoked(false)
              .approved(true)
              .apiKey(savedApiKey)
              .build()
          ).collect(Collectors.toList());
      applicationRepository.saveAll(applications);
    }
    return key;
  }

  public String revokeApiKey(String key) {
    var apiKey = apiKeyRepository.findByKey(key)
        .orElseThrow(() -> new EntityNotFoundException("No Api was found with the provided KEY"));
    apiKey.setRevoked(true);
    apiKey.setEnabled(false);
    apiKey.setApproved(false);
    apiKeyRepository.save(apiKey);

    // Once we revoke an ApiKey -> revoke all the applications
    apiKey.getApplications()
        .forEach(app -> {
          app.setApproved(false);
          app.setRevoked(true);
          app.setEnabled(false);
          applicationRepository.save(app);
        });
    return  key;
  }

  public boolean isAuthorized(String apiKey, String appName) {
    var apiOptional = apiKeyRepository
        .findByKeyAndApplicationName(apiKey, appName);
    if (apiOptional.isEmpty()) {
      return false;
    }
    var api = apiOptional.get();
    var application = api.getApplications()
        .stream()
        .filter(app -> appName.equals(app.getName()))
        .findFirst()
        .orElse(null);
    final boolean isApiKeyAuthorized = api.isApproved()
        && api.isEnabled()
        && !api.isRevoked()
        && (api.isNeverExpires() || LocalDateTime.now().isBefore(api.getExpirationDate()));
    final boolean isApplicationAuthorized = application != null
        && application.isEnabled()
        && application.isApproved()
        && !application.isRevoked();
    return isApiKeyAuthorized && isApplicationAuthorized;
  }

  private String generateRandomKey() {
    return UUID.randomUUID().toString();
  }
}
