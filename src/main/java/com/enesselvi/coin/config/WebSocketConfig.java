package com.enesselvi.coin.config;

import com.enesselvi.coin.common.WebSocketClientBase;
import com.enesselvi.coin.common.WebSocketClientFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;

@Configuration
public class WebSocketConfig {
    @Bean
    public StandardWebSocketClient standardWebSocketClient(){
        return new StandardWebSocketClient();
    }
}
