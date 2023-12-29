package com.zhoumoumou.yunmayi.config.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.RequestParameterBuilder;
import springfox.documentation.schema.ScalarType;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * @Description:
 * @Author: zhouqie
 * @date 2023/03/16
 */
@Configuration
public class SwaggerConfig {
    /**
     * 用于读取配置文件 application.yml 中 swagger 属性是否开启
     */
//    @Value("${swagger.enabled}")
//    Boolean swaggerEnabled;
    @Bean
    public Docket docket(Environment environment) {
        // 设置环境范围
        Profiles profiles = Profiles.of("dev", "test");
        // 如果在该环境返回内则返回：true，反之返回 false
        boolean flag = environment.acceptsProfiles(profiles);
        HashSet<String> strings = new HashSet<>();
        strings.add("http");
        strings.add("https");

        return new Docket(DocumentationType.OAS_30)
                .apiInfo(apiInfo())
                // 是否开启swagger
                .enable(true)
                .select()
                // 过滤条件，扫描指定路径下的文件
                .apis(RequestHandlerSelectors.basePackage("com.zhoumoumou.yunmayi.controller"))
                // 指定路径处理，PathSelectors.any()代表不过滤任何路径
                //.paths(PathSelectors.any())
                .build()
//                .globalRequestParameters(getGlobalRequestParameters())
                // 支持的通讯协议集合
                .protocols(strings)
                .securitySchemes(securitySchemes())
                .securityContexts(securityContexts());
    }

    /**
     * 设置请求参数
     *
     * @return
     */
    private List<RequestParameter> getGlobalRequestParameters() {

        List<RequestParameter> parameters = new ArrayList<>();
        parameters.add(new RequestParameterBuilder()
                .name("token")
                .description("传入token")
                .parameterIndex(-1)
                .required(false)
                .in(ParameterType.HEADER)
                .query(q -> q.model(m -> m.scalarModel(ScalarType.STRING)))
                .required(false)
                .build());
        return parameters;
    }

    private ApiInfo apiInfo() {
        /*作者信息*/
        Contact contact = new Contact("dues Lu Vult", "https:www.zhoumoumou.top", "1320226817@qq.com");
        return new ApiInfo(
                "云蚂蚁实习账本",
                "Spring Boot 集成 Swagger3 测试接口文档",
                "v1.0",
                "https:www.zhoumoumou.top",
                contact,
                "Apache 2.0",
                "http://www.apache.org/licenses/LICENSE-2.0",
                new ArrayList()
        );
    }

    private List<SecurityScheme> securitySchemes() {
        //设置请求头信息
        List<SecurityScheme> securitySchemes = new ArrayList<>();
        securitySchemes.add(new ApiKey("token", "token", "header"));
        return securitySchemes;
    }


    /**
     * 授权信息全局应用
     */
    private List<SecurityContext> securityContexts() {
        List<SecurityContext> securityContexts = new ArrayList<>();
        securityContexts.add(SecurityContext.builder()
                .securityReferences(defaultAuth())
                .forPaths(PathSelectors.any()).build());
        return securityContexts;
    }

//    private List<SecurityContext> securityContexts() {
//        //设置需要登录认证的路径
//        List<SecurityContext> result = new ArrayList<>();
//        result.add(getContextByPath("/brand/.*"));
//        return result;
//    }


    private SecurityContext getContextByPath(String pathRegex) {
        return SecurityContext.builder()
                .securityReferences(defaultAuth())
                .forPaths(PathSelectors.regex(pathRegex))
                .build();
    }


    private List<SecurityReference> defaultAuth() {
        List<SecurityReference> result = new ArrayList<>();
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        result.add(new SecurityReference("token", authorizationScopes));
        return result;
    }
}
