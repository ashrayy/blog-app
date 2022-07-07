package com.example.easynotes.config;

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
public class SwaggerConfig {

    @Bean
public Docket api(){
    return new Docket(DocumentationType.SWAGGER_2)
            .apiInfo(getInfo())
            .select().apis(RequestHandlerSelectors.any())
            .paths(PathSelectors.any())
            .build();
}

    private ApiInfo getInfo() {
    return new ApiInfo("Blog App APIs","By - Ashray",
            "1.0","Terms of Service",
            new Contact("AshRAy","","triash786@gmail.com"),
            "Licensed","", Collections.emptyList());

    }


}
