package com.amigoscode.apimanagement.application;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/api-management/applications")
@RequiredArgsConstructor
public class ApplicationController {

    private final ApplicationService applicationService;

    @DeleteMapping("{appName}/authorise/{apiKey}")
    public void revokeApplicationFromKey(
            @PathVariable("appName") String appName,
            @PathVariable("apiKey") String apiKey) {
        applicationService.revokeApplication(appName, apiKey);
    }

    @DeleteMapping("{appName}/authorise/{apiKey}")
    public void assignApplicationToApiKey(
            @PathVariable("appName") String appName,
            @PathVariable("apiKey") String apiKey) {
        applicationService.assignToApiKey(appName, apiKey);
    }
}
