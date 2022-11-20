package com.amigoscode.apigw.secutity;

import java.util.List;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.support.ServerWebExchangeUtils;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @author Ali Bouali
 */
@Component
public class ApiAuthorizationFilter implements GlobalFilter, Ordered {

  private final ApiService apiService;

  public ApiAuthorizationFilter(ApiService apiService) {
    this.apiService = apiService;
  }

  @Override
  public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
    List<String> apiKeyHeader = exchange.getRequest().getHeaders().get("apiKey");
    Route route = exchange.getAttribute(ServerWebExchangeUtils.GATEWAY_ROUTE_ATTR);
    if (route == null ||
        !StringUtils.hasLength(route.getId()) ||
        CollectionUtils.isEmpty(apiKeyHeader) ||
        apiService.isNotAuthorized(apiKeyHeader.get(0), route.getId())
    ) {
      throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,
          "You're not authorized to access this API");
    }
    return chain.filter(exchange);
  }

  @Override
  public int getOrder() {
    return Ordered.LOWEST_PRECEDENCE;
  }
}
