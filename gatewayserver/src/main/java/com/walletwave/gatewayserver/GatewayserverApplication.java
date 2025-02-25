package com.walletwave.gatewayserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;

import java.time.Duration;
import java.time.LocalDateTime;

@SpringBootApplication
public class GatewayserverApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatewayserverApplication.class, args);
	}
	@Bean
	public RouteLocator walletWaveRouteConfig(RouteLocatorBuilder routeLocatorBuilder){
		return routeLocatorBuilder
                .routes()
                .route(p-> p
						.path("/walletwave/accounts/**")
						.filters(f -> f.rewritePath("/walletwave/accounts/(?<segment>.*)", "/$[segment}")
								.addResponseHeader("X-Response-Time", LocalDateTime.now().toString())
						.circuitBreaker(config -> config.setName("accountsCircuitBreaker")
								.setFallbackUri("forward:/contactSupport")))
                        .uri("lb://ACCOUNTS"))
				.route(p-> p
				.path("/walletwave/loans/**")
						.filters(f -> f.rewritePath("/walletwave/loans/(?<segment>.*)", "/$[segment}")
								.addResponseHeader("X-Response-Time", LocalDateTime.now().toString())
								.retry(retryConfig -> retryConfig.setRetries(3)
										.setMethods(HttpMethod.GET)
										.setBackoff(Duration.ofMillis(100), Duration.ofMillis(1000), 2, true)))

				.uri("lb://LOANS"))
				.route(p-> p
						.path("/walletwave/cards/**")
						.filters(f -> f.rewritePath("/walletwave/cards/(?<segment>.*)", "/$[segment}")
								.addResponseHeader("X-Response-Time", LocalDateTime.now().toString()))
						.uri("lb://CARDS"))
                .build();
	}

}
