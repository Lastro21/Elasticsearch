package com.elasticsearch.elasticsearch.conf;

import com.elasticsearch.elasticsearch.models.TestUser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ConfigBeans {

    @Bean
    public TestUser testUser(){
        return new TestUser();
    }
    @Bean
    public HttpHeaders httpHeaders(){
        return new HttpHeaders();
    }
    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
