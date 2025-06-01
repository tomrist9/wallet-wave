package com.walletwave.cards;

import com.walletwave.cards.dto.CardsContactInfoDto;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;


@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
@EnableConfigurationProperties(value = {CardsContactInfoDto.class})
@OpenAPIDefinition(
        info = @Info(
                title = "Cards microservice REST API Documentation",
                description = "Wallet-wave Cards microservice REST API Documentation",
                version = "v1",
                contact = @Contact(
                        name = "Tomris Teymurlu",
                        email = "tutor@wallet-wave.com",
                        url = "https://www.wallet-wave.com"
                ),
                license = @License(
                        name = "Apache 2.0",
                        url = "https://www.wallet-wave.com"
                )
        ),
        externalDocs = @ExternalDocumentation(
                description = "Wallet-wave Cards microservice REST API Documentation",
                url = "https://www.wallet-wave.com/swagger-ui.html"
        )
)
public class CardsApplication {

    public static void main(String[] args) {
        SpringApplication.run(CardsApplication.class, args);
    }
}