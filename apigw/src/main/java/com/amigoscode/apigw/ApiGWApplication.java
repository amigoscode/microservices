package com.amigoscode.apigw;

import com.amigoscode.apigw.security.ApiKeyService;
import javax.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
@RequiredArgsConstructor
public class ApiGWApplication {

    private final ApiKeyService service;
    public static void main(String[] args) {
        SpringApplication.run(ApiGWApplication.class, args);
    }

    @PostConstruct
    public void initAppAuthKeys() {
        this.service.initialize();
    }
}
