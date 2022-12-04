package com.amigoscode.apimanagement;

import com.amigoscode.apimanagement.apikey.ApiKeyService;
import com.amigoscode.apimanagement.apikey.NewApiKeyRequest;
import com.amigoscode.clients.Application;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class ApiManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiManagementApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner (ApiKeyService apiKeyService) {
		return args -> {
			NewApiKeyRequest apiKeyRequest = new NewApiKeyRequest(
					"foo", "bar", List.of(Application.CUSTOMER)
			);
			String apiKey = apiKeyService.save(apiKeyRequest);
			System.out.println(apiKey);
			System.out.println(apiKeyRequest);
		};
	}


}
