package com.amigoscode.customer;

public record CustomerRegistrationRequest(
        String firstName,
        String lastName,
        String email) {
}
