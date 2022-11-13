package com.amigoscode.apimanagement.services;

import com.amigoscode.apimanagement.dto.ApiDto;
import com.amigoscode.apimanagement.models.Api;
import com.amigoscode.apimanagement.models.Application;
import com.amigoscode.apimanagement.repositories.ApiRepository;
import com.amigoscode.apimanagement.repositories.ApplicationRepository;
import java.time.LocalDateTime;
import java.util.UUID;
import java.util.stream.Collectors;
import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

/**
 * @author Ali Bouali
 */
@Service
@RequiredArgsConstructor
public class ApiService {

  private static final Integer EXPIRATION_DATE_IN_DAYS = 90;
  private final ApiRepository repository;
  private final ApplicationRepository applicationRepository;

  public String save(ApiDto apiDto) {
    // todo validate the object first
    final String apiKey = generateApiKey();
    final Api api = ApiDto.toEntity(apiDto);
    api.setKey(apiKey);
    api.setExpirationDate(LocalDateTime.now().plusDays(EXPIRATION_DATE_IN_DAYS));
    api.setNeverExpires(false);
    // todo (maybe) implement an approval mechanism
    api.setApproved(true);
    // todo (maybe) implement an approval mechanism to enable the api
    api.setEnabled(true);
    api.setCreatedDated(LocalDateTime.now());
    var savedApi = repository.save(api);
    // assign application to API key
    if (!CollectionUtils.isEmpty(apiDto.getApplications())) {
      var applications = apiDto.getApplications().stream()
          .map(app -> Application
              .builder()
              .name(app)
              // todo better implement an approval mechanism
              .enabled(true)
              .revoked(false)
              .approved(true)
              .api(savedApi)
              .build()
          ).collect(Collectors.toSet());
      applicationRepository.saveAll(applications);
    }
    return apiKey;
  }

  @Transactional
  public String revokeApi(String apiKey) {
    var api = repository.findByKey(apiKey)
        .orElseThrow(() -> new EntityNotFoundException("No api was found with the provided key"));
    api.setRevoked(true);
    api.setEnabled(false);
    api.setApproved(false);
    repository.save(api);
    // when revoke an API-KEY -> App applications should be revoked too
    api.getApplications()
        .forEach(app -> {
          app.setApproved(false);
          app.setRevoked(true);
          app.setEnabled(false);
          applicationRepository.save(app);
        });
    return apiKey;
  }

  @Transactional
  public boolean isAuthorized(String apiKey, String appName) {
    var apiOptional = repository.findByKeyAndApplicationName(apiKey, appName);
    if (apiOptional.isEmpty()) {
      return false;
    }
    var api = apiOptional.get();
    var application = api.getApplications()
        .stream()
        .findFirst()
        .orElse(null);
    final boolean isApikeyAuthorized =  api.isApproved()
        && api.isEnabled()
        && !api.isRevoked()
        && (api.isNeverExpires() || LocalDateTime.now().isBefore(api.getExpirationDate()));
    final boolean isAppAuthorized = isApplicationAuthorized(application);

    return  isApikeyAuthorized && isAppAuthorized;
  }

  private boolean isApplicationAuthorized(final Application application) {
    return application != null
        && application.isEnabled()
        && application.isApproved()
        && !application.isRevoked();
  }

  private String generateApiKey() {
    return UUID.randomUUID().toString();
  }

}
