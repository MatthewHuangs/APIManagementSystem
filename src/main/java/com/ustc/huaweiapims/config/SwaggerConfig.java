package com.ustc.huaweiapims.config;

import com.google.common.base.Predicates;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.async.DeferredResult;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author matthew huang
 * @description
 * @date 2019/6/9 5:40 PM
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig { // extends WebSecurityConfigurerAdapter {
    @Bean
    public Docket BuildDocket(){

        return new Docket(DocumentationType.SWAGGER_2)
                .genericModelSubstitutes(DeferredResult.class)
                .useDefaultResponseMessages(false)
                .forCodeGeneration(false)
                .pathMapping("/")
                .select()
                .paths(Predicates.not(PathSelectors.regex("/login.*")))
                .paths(Predicates.not(PathSelectors.regex("/logout.*")))
                .paths(Predicates.not(PathSelectors.regex("/logoutsuccess.*")))
                .paths(Predicates.not(PathSelectors.regex("/view.*")))
                .paths(Predicates.not(PathSelectors.regex("/jsonconvert.*")))
                .paths(Predicates.not(PathSelectors.regex("/index.*")))
                .paths(Predicates.not(PathSelectors.regex("/error.*")))
                .build()
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {

        Contact contact = new Contact("USTCER", "https://github.com/MatthewHuangs/SwaggerAPIManagement-HUAWEI", "matthew_hs@outlook.com");
        return new ApiInfoBuilder()
                .title("华为API生态管理系统")
                .description("基于Swagger2的API查看、测试、代码生成于一体的API管理系统")
                .termsOfServiceUrl("www.huaweiapimanagementsystem.com")
                .license("© Design & Power by USTC")
                .licenseUrl("http://www.apache.org/licenses/LICENSE-2.0.txt")
                .contact(contact)
                .version("1.0.0")
                .build();
    }
}
