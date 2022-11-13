package com.amigoscode.apigw;

import com.amigoscode.apigw.security.ApiService;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableEurekaClient
@SpringBootApplication
@EnableFeignClients
public class ApiGWApplication {

    private final ApiService service;

    public ApiGWApplication(
    @Qualifier("apiKeyService") ApiService service) {
        this.service = service;
    }

    public static void main(String[] args) {
        SpringApplication.run(ApiGWApplication.class, args);
    }

    @PostConstruct
    public void initAppAuthKeys() {
        this.service.initialize();
    }
}
