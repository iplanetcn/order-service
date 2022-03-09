package com.phenix.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * Swagger 配置文件
 * <p>
 * json: http://xxx:8080/v2/api-docs
 * doc: http://xxx:8080/swagger-ui.html
 */
@Configuration
@Profile({"dev"})
public class SwaggerConfig {
    @Bean
    public GroupedOpenApi apiGroup() {
        return GroupedOpenApi
                .builder()
                .group("API")
                .pathsToMatch("/api/**")
                .build();
    }

    @Bean
    public OpenAPI apiInfo() {
        return new OpenAPI()
                .info(new Info().title("Order Service APIs")
                        .description("The project implemented by spring boot")
                        .license(new License().name("Licence2.0").url("https://www.apache.org/licenses/LICENSE-2.0.html"))
                        .version("v1.0")
                        .contact(new Contact().name("john")
                                .email("iplanetcn@gmail.com")
                        )
                );
    }
}

