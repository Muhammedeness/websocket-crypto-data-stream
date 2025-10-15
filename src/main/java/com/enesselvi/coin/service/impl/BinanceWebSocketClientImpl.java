package com.enesselvi.coin.service.impl;

import com.enesselvi.coin.common.WebSocketClientBase;
import com.enesselvi.coin.handler.CustomWebSocketHandler;
import com.enesselvi.coin.service.ParserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;

import java.net.URI;
import java.util.concurrent.CompletableFuture;

@Service
public class BinanceWebSocketClientImpl extends WebSocketClientBase {

    private static final Logger LOGGER = LoggerFactory.getLogger(BinanceWebSocketClientImpl.class);

    private final ParserService parserService;
    private final StandardWebSocketClient client;


    public BinanceWebSocketClientImpl(ParserService parserService, StandardWebSocketClient client) {
        this.parserService = parserService;
        this.client = client;
    }

    @Override
    public void connect(String symbol, String uri) {
        try {
            LOGGER.info("Connecting To Binance Websocket: {}",uri);
            CompletableFuture<WebSocketSession> sessionFuture = client.execute(
                    new CustomWebSocketHandler(parserService , this  , symbol) , String.valueOf(URI.create(uri))
            );
            sessionFuture.exceptionally(ex ->{
                LOGGER.error("FAILED TO CONNECT TO BINANCE WEBSOCKET" , ex);
                return null;
            });

        } catch (Exception e) {
            LOGGER.error("Error Connecting to Binance Websocket" , e);
        }
    }

    @Override
    public String getSubscribeJson(String symbol) {
        return String.format("""
        {
          "method": "SUBSCRIBE",
          "params": [
            "%1$s@trade"
          ],
          "id": 1
        }
        """, symbol.toLowerCase());    }
}
