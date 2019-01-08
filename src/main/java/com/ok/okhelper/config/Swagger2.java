package com.ok.okhelper.config;

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
 * Author: zc
 * Date: 2018/4/21
 * Description:
 */
@Configuration
@EnableSwagger2
public class Swagger2 {

    @Bean
    public Docket createRestApi() {
        //添加head参数
//        ParameterBuilder tokenPar = new ParameterBuilder();
//        List<Parameter> pars = new ArrayList<Parameter>();
//        tokenPar.name("token").description("令牌").modelRef(new ModelRef("string")).parameterType("header").required(false).build();
//        pars.add(tokenPar.build());

        return new Docket(DocumentationType.SWAGGER_2)
                .pathMapping("/")
                .host("127.0.0.1")
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.ok.okhelper.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("OK Restful 文档")
                .description("一个基于SpringBoot Restful 服务")
                .contact(new Contact("okhelper", "https://github.com/okhelperTeam/okhelper-service", "zc1217zc@126.com"))
                .version("1.0")
                .build();
    }
}

