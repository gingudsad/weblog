package com.gingu.admin.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

@Configuration
@EnableSwagger2WebMvc
@Profile("dev")
public class Knife4jAdminConfig {

    @Bean("adminApi")
    public Docket createApiDoc(){
        Docket docket = new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(buildApiInfo())
                .groupName("admin 后台接口")
                .select()
                .apis(RequestHandlerSelectors.basePackage("ccom.gingu.weblogadmin.controller"))
                .paths(PathSelectors.any())
                .build();
        return docket;
    }

    private ApiInfo buildApiInfo(){
        return new ApiInfoBuilder()
                .title("weblog博客前台接口文档")
                .description("前后端分离")
                .termsOfServiceUrl("https://www.gingu.com/")
                .contact(new Contact("gingu","https://www.gingu.com","g13042786791@163.com"))
                .version("1.0")
                .build();
    }
}
