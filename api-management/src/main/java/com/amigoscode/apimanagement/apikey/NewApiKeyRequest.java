package com.amigoscode.apimanagement.apikey;

import com.amigoscode.clients.Application;

import java.util.List;

public record NewApiKeyRequest (
        String client,
        String description,
        List<Application> applications) {
}
