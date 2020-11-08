package sk.gw.jo2o.petshop.config;

import static java.util.Collections.emptyList;
import static springfox.documentation.builders.PathSelectors.regex;
import static springfox.documentation.builders.RequestHandlerSelectors.basePackage;
import static springfox.documentation.spi.DocumentationType.SWAGGER_2;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.service.*;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket petshop() {
        return new Docket(SWAGGER_2)
                .apiInfo(createApiInfo())
                .securityContexts(List.of(createSecurityContext()))
                .securitySchemes(List.of(createApiKey()))
                .select()
                .apis(basePackage("sk.gw.jo2o.petshop.rest"))
                .paths(regex("/.*"))
                .build();
    }

    private ApiKey createApiKey() {
        return new ApiKey("JWT", "Authorization", "header");
    }

    private SecurityContext createSecurityContext() {
        return SecurityContext.builder()
                .securityReferences(defaultAuth())
                .build();
    }

    private List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        return List.of(new SecurityReference("JWT", authorizationScopes));
    }

    private ApiInfo createApiInfo() {
        return new ApiInfo("PetShop", "E-shop for pet lovers!", "1.0", "", createContact(), "", "", emptyList());
    }

    private Contact createContact() {
        final String name = "Jozef Mikus";
        final String url = "https://www.linkedin.com/in/jozef-mikus-53165859/";
        final String email = "jozo.mikus@gmail.com";
        return new Contact(name, url, email);
    }

}
