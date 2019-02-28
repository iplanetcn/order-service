package com.phenix.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Swagger 配置文件
 *
 * json: http://xxx:8080/v2/api-docs
 * doc: http://xxx:8080/swagger-ui.html
 */
@Configuration
@EnableSwagger2
//@Profile("dev")
public class SwaggerConfig {
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo())
                                                      .select()
                                                      .apis(RequestHandlerSelectors.basePackage("com.phenix.controller"))
                                                      .paths(PathSelectors.any())
                                                      .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("Order Service APIs")
                                   .description("The project implemented by spring boot")
                                   .contact(new Contact("John", "http://moonvsriver.com", "iplanetcn@gmail.com"))
                                   .license("Licence2.0")
                                   .licenseUrl("https://www.apache.org/licenses/LICENSE-2.0.html")
                                   .version("1.0")
                                   .build();
    }
}
