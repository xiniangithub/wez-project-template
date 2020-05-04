package com.wez.swagger.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@ComponentScan(basePackages= {"com.wez.ctrl"})
@EnableWebMvc
public class Swagger2Config {
    
//    @Value("${swagger.show}")
    private boolean swaggerShow = true;

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .enable(swaggerShow) // 是否显示
                .select()
                .apis(RequestHandlerSelectors.any())
                .build()
                .apiInfo(apiInfo());
    }
    
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("任务系统接口文档")// 大标题 title
                .description("对外开放接口")// 小标题
                .version("1.0.0") // 版本号
//                .termsOfServiceUrl("http://xxx.xxx.com")// 终端服务程序
//                .license("LICENSE")// 链接显示文字
//                .licenseUrl("http://xxx.xxx.com")// 网站链接
                .build();
    }

}
