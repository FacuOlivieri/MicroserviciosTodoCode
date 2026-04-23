package com.todocodeacademy.appointment.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfig {

    @Bean("consumeApi") //Se puede usar cualquier nombre, con este alias lo vamos a llamar y que Spring se encargue de utilizarlo
    public RestTemplate registerRestTemplate() {
        return new RestTemplate();
    }

}
