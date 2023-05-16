package com.user.service.api.config;

import io.micronaut.context.annotation.Bean;
import io.micronaut.context.annotation.Factory;
import jakarta.inject.Singleton;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.web.client.RestTemplate;

@Factory
public class RestTemplateFactory {


    @Bean
    @Singleton
    @LoadBalanced
    public RestTemplate v8Engine() {
      return new RestTemplate();
    }


}