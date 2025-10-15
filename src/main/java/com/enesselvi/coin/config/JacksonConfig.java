package com.enesselvi.coin.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JacksonConfig {

    /**
     * Spring Context manage ObjectMapper for us , to do that we need to add this bean to context
     */
    @Bean
    public ObjectMapper objectMapper(){
        return new ObjectMapper();
    }

}
