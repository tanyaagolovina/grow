package com.tetianaholovina.weatherforecast.config;

import org.h2.server.web.WebServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class BeansConfig {

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

    @Bean
    ServletRegistrationBean h2ServletRegistrationBean(){
        ServletRegistrationBean registrationBean = new ServletRegistrationBean(new WebServlet());

        registrationBean.addUrlMappings("/console/*");

        return registrationBean;
    }
}
