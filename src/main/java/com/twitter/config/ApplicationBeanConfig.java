package com.twitter.config;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.cognitoidp.AWSCognitoIdentityProvider;
import com.amazonaws.services.cognitoidp.AWSCognitoIdentityProviderClientBuilder;

import com.twitter.utils.AppConfig;
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

    @Bean
    public AWSCognitoIdentityProvider awsCognitoIdentityProvider() {
        AWSCredentials credentials = new BasicAWSCredentials(
                (String)AppConfig.getParameter("Beta", "AWS_ID"),
                (String)AppConfig.getParameter("Beta", "AWS_KEY"));
        AWSCredentialsProvider credProvider = new AWSStaticCredentialsProvider(credentials);
        AWSCognitoIdentityProvider client = AWSCognitoIdentityProviderClientBuilder.standard()
                .withCredentials(credProvider)
                .withRegion((String)AppConfig.getParameter("Beta", "region"))
                .build();
        return client;
    }
}
