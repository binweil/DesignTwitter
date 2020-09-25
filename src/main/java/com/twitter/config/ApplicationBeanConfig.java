package com.twitter.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

@Configuration
@ComponentScan("com.twitter")
@EnableWebMvc
public class ApplicationBeanConfig {

    @Bean(name="requestMappingHandlerMapping")
    public RequestMappingHandlerMapping requestMappingHandlerMapping () {
        RequestMappingHandlerMapping mapping = new RequestMappingHandlerMapping();
        return mapping;
    }
}
