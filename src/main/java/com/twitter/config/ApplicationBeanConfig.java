package com.twitter.config;

import com.twitter.utils.SerializationUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

@Configuration
@ComponentScan(basePackages = {"com.twitter.controller"})
@EnableWebMvc
public class ApplicationBeanConfig {

    // Bean For EndpointDocController
    @Bean(name="customRequestMappingHandlerMapping")
    public RequestMappingHandlerMapping customRequestMappingHandlerMapping () {
        RequestMappingHandlerMapping mapping = new RequestMappingHandlerMapping();
        return mapping;
    }

    @Bean
    public AuthorizeAdvice authorizeAdvice () {
        return new AuthorizeAdvice();
    }

    @Bean
    public SerializationUtils serializationUtils () {
        return new SerializationUtils();
    }
}
