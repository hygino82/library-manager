package br.dev.hygino.config;

import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

	@Bean
	OpenAPI customOpenAPI() {
		return new OpenAPI().info(new Info().title("Minha API Spring Boot").version("1.0.0")
				.description("Documentação gerada automaticamente pelo Springdoc OpenAPI"));
	}
}