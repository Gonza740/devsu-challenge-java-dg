package com.devsu.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI myOpenAPI(SwaggerProperties swaggerProperties) {

        var contact = new Contact();
        contact.setEmail(swaggerProperties.getContact().getEmail());
        contact.setName(swaggerProperties.getContact().getName());
        contact.setUrl(swaggerProperties.getContact().getUrl());

        var mitLicense = new License().name("MIT License").url("https://choosealicense.com/licenses/mit/");

        var info = new Info()
                .title(swaggerProperties.getInfo().getTitle())
                .version(swaggerProperties.getInfo().getVersion())
                .contact(contact)
                .description(swaggerProperties.getInfo().getDescription())
                .license(mitLicense);

        return new OpenAPI()
                .servers(Collections.singletonList(new Server().url(swaggerProperties.getGateway().getUrl())))
                .info(info);
    }

    @Bean
    public GroupedOpenApi apiV1() {
        return GroupedOpenApi.builder()
                .group("version-1")
                .pathsToMatch("/api/v1/**")
                .build();
    }
}

