package com.amigoscode.apimanagement.apikey;

import com.amigoscode.apimanagement.application.Application;
import com.amigoscode.apimanagement.application.ApplicationRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ApiKeyService {

    private static final Integer EXPIRATION_DATE_IN_DAYS = 90;

    private final ApiKeyRepository repository;
    private final ApplicationRepository applicationRepository;
    private final KeyGenerator generateApiKey;

    @Transactional
    public String save(NewApiKeyRequest apiKeyRequest) {
        // todo validate the object first
        ApiKey api = new ApiKey();
        api.setClient(apiKeyRequest.client());
        api.setDescription(apiKeyRequest.description());

        String apiKey = generateApiKey.generate();
        api.setKey(apiKey);

        api.setExpirationDate(LocalDateTime.now().plusDays(EXPIRATION_DATE_IN_DAYS));
        api.setNeverExpires(false);
        api.setApproved(true);
        api.setEnabled(true);
        api.setCreatedDated(LocalDateTime.now());

        var savedApi = repository.save(api);

        // assign application to API key
        Set<Application> applications = Optional.ofNullable(apiKeyRequest.applications())
                .orElse(List.of())
                .stream().map(app -> Application.builder().name(app)
                        .enabled(true).revoked(false).approved(true).apiKey(savedApi).build()).collect(Collectors.toUnmodifiableSet());

        applicationRepository.saveAll(applications);

        return apiKey;
    }

    @Transactional
    public void revokeApi(String apiKey) {
        var api = repository.findByKey(apiKey).orElseThrow(() -> new EntityNotFoundException("No api was found with the provided key"));
        api.setRevoked(true);
        api.setEnabled(false);
        api.setApproved(false);
        repository.save(api);

        // when revoke an API-KEY -> App applications should be revoked too
        api.getApplications().forEach(app -> {
            app.setApproved(false);
            app.setRevoked(true);
            app.setEnabled(false);
            applicationRepository.save(app);
        });
    }

    @Transactional
    public boolean isAuthorized(String apiKey, com.amigoscode.clients.Application appName) {
        var apiOptional = repository.findByKeyAndApplicationName(apiKey, appName);
        if (apiOptional.isEmpty()) {
            return false;
        }

        var key = apiOptional.get();

        return key.getApplications()
                .stream()
                .filter(app -> app.getName().equals(appName))
                .findFirst()
                .map(a -> a.isEnabled() &&
                        a.isApproved() &&
                        !a.isRevoked() &&
                        key.isApproved() &&
                        key.isEnabled() &&
                        !key.isRevoked() &&
                        (key.isNeverExpires() || LocalDateTime.now().isBefore(key.getExpirationDate())))
                .orElse(false);
    }

}
