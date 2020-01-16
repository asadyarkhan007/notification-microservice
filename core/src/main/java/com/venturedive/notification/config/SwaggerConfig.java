package com.venturedive.notification.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

/** Created by Safeer Ansari on 20/02/2018. */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

  @Value("${api.version}")
  String apiVersion;

  @Value("${api.swaggerEnable}")
  Boolean swaggerEnable;

  @Bean
  public Docket api() {
    return new Docket(DocumentationType.SWAGGER_2)
        .select()
        .apis(RequestHandlerSelectors.basePackage("com.venturedive.microservice.notification"))
        .paths(PathSelectors.any())
        .build()
        .enable(swaggerEnable)
        .apiInfo(apiInfo());
  }

  private ApiInfo apiInfo() {
    return new ApiInfo(
        "VentureDive Notification APIs",
        "Allow to do CRUD operation on Notification services.",
        "API " + apiVersion,
        "Terms of service",
        new Contact("VentureDive", "https://venturedive.com", "safeer.ansari@venturedive.com"),
        "License of API",
        "API license URL",
        Collections.emptyList());
  }
}
