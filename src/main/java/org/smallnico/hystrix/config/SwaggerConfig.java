package org.smallnico.hystrix.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket managerDocument() {
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo())
                .groupName("simple")
                .select()
                .apis(RequestHandlerSelectors.basePackage("org.smallnico.hystrix.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Nico")
                .description("hystrix test")
                .version("1.0.2")
                .build();
    }
}
