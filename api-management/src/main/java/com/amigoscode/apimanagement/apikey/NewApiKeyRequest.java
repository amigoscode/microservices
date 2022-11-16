package com.amigoscode.apimanagement.apikey;

import java.util.List;

public record NewApiKeyRequest (
        String name,
        String description,
        List<String> applications) {
}
