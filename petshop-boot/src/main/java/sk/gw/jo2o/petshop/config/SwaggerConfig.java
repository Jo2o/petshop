package sk.gw.jo2o.petshop.config;

import static java.util.Collections.emptyList;
import static springfox.documentation.builders.PathSelectors.regex;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket repeng() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .paths(regex("/.*"))
                .build()
                .apiInfo(createApiInfo());
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
