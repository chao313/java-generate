package demo.spring.boot.demospringboot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.async.DeferredResult;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Created by hwc on 2017/1/16.
 */
@Configuration
@EnableSwagger2
public class Swagger2Configuration {
    @Bean
    public Docket demoApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors
                        .basePackage("demomaster"))
                .paths(PathSelectors.any())
                .build()
                .genericModelSubstitutes(DeferredResult.class)//异步http请求
                .forCodeGeneration(true)
                .pathMapping("/")
                .apiInfo(apiInfo())
                .useDefaultResponseMessages(false)
                .groupName("demoMaster")
                ;
    }

    @Bean
    public Docket actuateApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors
                        .basePackage("org.springframework.boot.actuate"))
                .paths(PathSelectors.any())
                .build()
                .genericModelSubstitutes(DeferredResult.class)//异步http请求
                .forCodeGeneration(true)
                .pathMapping("/")
                .apiInfo(apiInfo())
                .useDefaultResponseMessages(false)
                .groupName("actuate")
                ;
    }

    @Bean
    public Docket innerApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors
                        .basePackage("demo.spring.boot.demospringboot.controller"))
                .paths(PathSelectors.any())
                .build()
                .genericModelSubstitutes(DeferredResult.class)//异步http请求
                .forCodeGeneration(true)
                .pathMapping("/")
                .apiInfo(apiInfo())
                .useDefaultResponseMessages(false)
                .groupName("innerApi")
                ;
    }

    /**
     * api描述信息
     *
     * @return
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("SwaggerDemo")
                .version("0.1")//本次发布的版本
                .build();
    }
}
