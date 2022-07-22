package br.com.cleo.AluraAPi.config.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class OpenApi30Config {

        @Bean
        public OpenAPI customOpenAPI() {
                return new OpenAPI()
                                .info(new Info().contact(new Contact().name("Cléo Cordeiro")
                                                .url("https://www.linkedin.com/in/cleocordeiro/"))
                                                .title("API Alura").version("1.0")
                                                .description("API Desesenvolvida Na Imersão Java do Alura"));
        }

}
