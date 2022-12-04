package com.amigoscode.apimanagement.application;

import com.amigoscode.clients.Application;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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

    @PutMapping("{appName}/authorise/{apiKey}")
    public void assignApplicationToApiKey(
            @PathVariable("appName") Application appName,
            @PathVariable("apiKey") String apiKey) {
        applicationService.assignToApiKey(appName, apiKey);
    }
}
