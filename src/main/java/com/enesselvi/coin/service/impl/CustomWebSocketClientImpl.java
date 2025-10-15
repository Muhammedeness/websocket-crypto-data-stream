package com.enesselvi.coin.service.impl;

import com.enesselvi.coin.config.CustomWebSocketHandler;
import com.enesselvi.coin.service.CustomWebSocketClient;
import com.enesselvi.coin.service.ParserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;

import java.net.URI;
import java.util.concurrent.CompletableFuture;

@Service
public class CustomWebSocketClientImpl implements CustomWebSocketClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomWebSocketClientImpl.class);

    private final ParserService parserService;
    private final StandardWebSocketClient client;

    public CustomWebSocketClientImpl(ParserService parserService, StandardWebSocketClient client) {
        this.parserService = parserService;
        this.client = client;
    }

    @Override
    public void connect(String symbol) {
        try {
            String uri = String.format("wss://stream.binance.com:9443/ws/%s@trade" , symbol.toLowerCase());
            LOGGER.info("Connecting To Binance Websocket: {}",uri);
            CompletableFuture<WebSocketSession> sessionFuture = client.execute(
                    new CustomWebSocketHandler(parserService) , String.valueOf(URI.create(uri))
            );
            sessionFuture.exceptionally(ex ->{
                LOGGER.error("FAILED TO CONNECT TO BINANCE WEBSOCKET" , ex);
                return null;
            });
        }catch (Exception e){
            LOGGER.error("Error Connecting to Binance Websocket" , e);
        }
    }
}
