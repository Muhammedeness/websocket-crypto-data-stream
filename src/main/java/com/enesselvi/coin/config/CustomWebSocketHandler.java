package com.enesselvi.coin.config;

import com.enesselvi.coin.service.ParserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;

public class CustomWebSocketHandler  implements WebSocketHandler {


    private static final Logger LOGGER   = LoggerFactory.getLogger(CustomWebSocketHandler.class);

    //Constructor
    private final ParserService parserService;

    public CustomWebSocketHandler(ParserService parserService){
        this.parserService = parserService;
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        LOGGER.info("CONNECTION ESTABLISHED WITH BINANCE SOCKET | sessionId: {}" , session.getId());

    }

    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
        LOGGER.info("RECEIVED MESSAGE FROM : {}  | MESSAGE : {}" , session.getId(),message.getPayload());
        parserService.ParseJsonToDto((String)message.getPayload());
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        //LOGGER.error("Exception error on session: {} | Error: {}", session.getId() , exception);
        LOGGER.error(String.format("Exception ocurred while handling | WebSocket SessionId: {}, Error : {}" , session.getId() , exception));
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
        LOGGER.info("DISCONNECTED WITH BINANCE SOCKET | sessionId: {}, closeStatus: {}" , session.getId() , closeStatus);

    }

    @Override
    public boolean supportsPartialMessages() {
        return false;
    }
}
