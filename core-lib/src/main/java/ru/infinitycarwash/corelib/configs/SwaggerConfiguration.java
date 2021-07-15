package ru.infinitycarwash.corelib.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;


import java.util.Collections;

@Configuration
public class SwaggerConfiguration {

    @Bean
    public Docket api(){
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("ru.infinitycarwash"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(metaData());
    }

    protected ApiInfo metaData(){
        return new ApiInfo(
                "Infinty-Carwash",
                "Demo project",
                "1",
                "Terms of service",
                new Contact("Maksim", "", "fors76@mail.ru"),
                "Licence", "", Collections.emptyList()
        );
    }
}
