package com.ustc.huaweiapims.config;

import org.jasig.cas.client.authentication.AuthenticationFilter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * @author matthew huang
 * @description
 * @date 2019/6/3 4:49 PM
 */
// 详细设计、编码实现（图）、用户手册
@Configuration
public class CASConfig {
    @Value("${cas.server-url-prefix}")
    private String serverUrlPrefix;
    @Value("${cas.server-login-url}")
    private String serverLoginUrl;
    @Value("${cas.client-host-url}")
    private String clientHostUrl;

    private final String ignoreURL = "/logoutsuccess|/index|/view/*|/favicon.ico|/static/*|/jsonconvert|" +
        "/v2/api-docs|/configuration/ui|/swagger-resources|/configuration/security|/swagger-ui.html|/webjars/*";
    /**
     * 授权过滤器
     * @return
     */
    @Bean
    public FilterRegistrationBean filterAuthenticationRegistration() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new AuthenticationFilter());
        // 设定匹配的路径
        registration.addUrlPatterns("/*");
        Map<String,String> initParameters = new HashMap();
        initParameters.put("casServerLoginUrl", serverUrlPrefix);
        initParameters.put("serverName", clientHostUrl);
        //忽略的url，"|"分隔多个url
        initParameters.put("ignorePattern", ignoreURL);
        registration.setInitParameters(initParameters);
        // 设定加载的顺序
        registration.setOrder(1);
        return registration;
    }
}
