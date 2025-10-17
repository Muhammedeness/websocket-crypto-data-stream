package com.enesselvi.coin.service.impl;

import com.enesselvi.coin.common.WebSocketClientBase;
import com.enesselvi.coin.common.WebSocketSessionManager;
import com.enesselvi.coin.handler.CustomWebSocketHandler;
import com.enesselvi.coin.service.ParserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;

import java.net.URI;
import java.util.concurrent.CompletableFuture;

@Service
public class BinanceWebSocketClientImpl extends WebSocketClientBase {

    private static final Logger LOGGER = LoggerFactory.getLogger(BinanceWebSocketClientImpl.class);

    private final ParserService parserService;
    private final StandardWebSocketClient client;
    private final WebSocketSessionManager sessionManager;
    private  CompletableFuture<String> sessionId;


    public BinanceWebSocketClientImpl(ParserService parserService, StandardWebSocketClient client, WebSocketSessionManager sessionManager) {
        this.parserService = parserService;
        this.client = client;
        this.sessionManager = sessionManager;
    }

    @Override
    public CompletableFuture<String> connect(String symbol, String uri) {
        try {
            LOGGER.info("Connecting To Binance Websocket: {}",uri);
            CompletableFuture<WebSocketSession> sessionFuture = client.execute(
                    new CustomWebSocketHandler(parserService , this  , symbol , sessionManager) , String.valueOf(URI.create(uri))
            );
            sessionFuture.exceptionally(ex ->{
                LOGGER.error("FAILED TO CONNECT TO BINANCE WEBSOCKET" , ex);
                return null;
            });
            return sessionFuture.thenApply(session -> session.getId());

        } catch (Exception e) {
            LOGGER.error("Error Connecting to Binance Websocket" , e);
            return null;
        }
    }

    @Override
    public void disconnect(String sessionId , String symbol) {
        try {
            WebSocketSession session = sessionManager.getSession(sessionId);
            if (session.isOpen()) {
                LOGGER.info("Disconnecting websocket , {}" ,sessionId);
                this.getUnsubscribeJson(symbol);
                LOGGER.info("UNSUBSCRIBED");
                session.close(CloseStatus.NORMAL);
            }
        }catch (Exception e){
            LOGGER.error("Error DisConnecting  Websocket" , e);
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

    @Override
    public String getUnsubscribeJson(String symbol) {
        return String.format("""
        {
          "method": "UNSUBSCRIBE",
          "params": [
            "%1$s@trade"
          ],
          "id": 1
        }
        """  , symbol.toLowerCase());
    }
}
