package com.amigoscode.apigw.security;


import com.amigoscode.clients.apimanagement.ApiManagementClient;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service("main")
@AllArgsConstructor
public class ApiKeyAuthorizationCheckerImpl implements ApiKeyAuthorizationChecker {

    private final ApiManagementClient apiManagementClient;

    @Override
    public boolean isAuthorized(String apiKey, String applicationName) {
        return apiManagementClient.isKeyAuthorizedForApplication(
                apiKey,
                applicationName
        ).isAuthorized();
    }

}
