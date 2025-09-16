package com.walletwave;

import com.walletwave.dto.AccountsContactInfoDto;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableFeignClients
/*@ComponentScans({ @ComponentScan("com.walletwave.accounts.controller") })
@EnableJpaRepositories("com.walletwave.accounts.repository")
@EntityScan("com.walletwave.accounts.model")*/
@EnableConfigurationProperties(value = {AccountsContactInfoDto.class})
@OpenAPIDefinition(
		info=@Info(
				title = "Accounts microservice REST API Documentation",
                version = "v1",
                description = "Wallet-wave Accounts Microservice REST API Documentation",
				contact = @Contact(
						name="Tomris Teymurlu",
						email = "tomristt9@gmail.com",
                        url = "https://github.com/tomrist9/wallet-wave/accounts"
				),
				license=@License(
					name="Apache 2.0"
				)
		),
		externalDocs = @ExternalDocumentation(
				description = "Find more info here",
                url = "https://github.com/tomrist9/wallet-wave/blob/main/README.md"
		)
)
public class AccountsApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccountsApplication.class, args);
	}

}
