/**
 * FileName: SwaggerConfig
 * Author:   马靖
 * Date:     2018/7/27 10:22
 * Description: Swagger的配置
 */
package com.zfsoft.boot.zhjx.setup.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


/**
 * 〈一句话功能简述〉<br>
 * 〈Swagger的配置〉
 *
 * @author Majing
 * @create 2018/12/05 10:22
 * @since 1.0.0
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    //    @Bean
//    public Docket docket() {
//        return new Docket(DocumentationType.SWAGGER_2).groupName("学工系统")
//                .apiInfo(new ApiInfoBuilder().title("学工系统接口文档").version("1.0").build())
//                .select().paths(PathSelectors.ant("/api/**")).build();
//    }
    @Bean
    public Docket test() {
        return new Docket(DocumentationType.SWAGGER_2).groupName("科技馆")
                .apiInfo(apiInfo())
                .select().paths(PathSelectors.ant("/kejiguan/**")).build();
    }
    private ApiInfo apiInfo() {
        Contact contact = new Contact("Majing", "http://127.0.0.1:8080", "751496272@qq.com");
        return new ApiInfoBuilder()
                .title("科技馆API接口")
                .description("科技馆API接口")
                .contact(contact)
                .version("1.0.0")
                .build();
    }
}
