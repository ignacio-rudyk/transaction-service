package com.accenture.transactionservice;

import com.google.gson.Gson;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfig {

    @Bean
    public Mapper getMapper(){
        return new DozerBeanMapper();
    }

    @LoadBalanced
    @Bean
    public RestTemplate registerRestTemplate(){return new RestTemplate();}

    @Bean
    public Gson getGson(){
        return new Gson();
    }

}
