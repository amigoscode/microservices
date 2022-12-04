package com.amigoscode.apigw.security;


import static org.springframework.util.CollectionUtils.isEmpty;

import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.support.ServerWebExchangeUtils;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class ApiAuthorizationFilter
        implements GlobalFilter, Ordered {

    private final ApiKeyAuthorizationChecker apiKeyAuthorizationChecker;

    public ApiAuthorizationFilter(
            @Qualifier("main") ApiKeyAuthorizationChecker apiKeyAuthorizationChecker) {
        this.apiKeyAuthorizationChecker = apiKeyAuthorizationChecker;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        List<String> apiKeyHeader = exchange.getRequest().getHeaders().get("X-Api-Key");
        Route route = exchange.getAttribute(ServerWebExchangeUtils.GATEWAY_ROUTE_ATTR);

        if (
               isEmpty(apiKeyHeader) ||
                !apiKeyAuthorizationChecker.isAuthorized(apiKeyHeader.get(0), route.getUri().getHost())
        ) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "You're not authorized to access this API");
        }

        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return Ordered.LOWEST_PRECEDENCE;
    }

}
