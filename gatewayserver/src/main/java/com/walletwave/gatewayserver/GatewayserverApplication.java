package com.walletwave.gatewayserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

import static ch.qos.logback.classic.spi.ThrowableProxyVO.build;

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
                        .filters(f -> f.rewritePath("/walletwave/accounts/(?<segment>.*)","/$[segment}"))
                        .uri("lb://ACCOUNTS"))
				.route(p-> p
				.path("/walletwave/loans/**")
				.filters(f -> f.rewritePath("/walletwave/loans/(?<segment>.*)","/$[segment}"))
				.uri("lb://LOANS"))
				.route(p-> p
						.path("/walletwave/cards/**")
						.filters(f -> f.rewritePath("/walletwave/cards/(?<segment>.*)","/$[segment}"))
						.uri("lb://CARDS"))
                .build();
	}

}
