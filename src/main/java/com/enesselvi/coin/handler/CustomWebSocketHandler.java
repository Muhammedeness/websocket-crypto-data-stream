package com.enesselvi.coin.handler;

import com.enesselvi.coin.common.WebSocketClientBase;
import com.enesselvi.coin.common.WebSocketSessionManager;
import com.enesselvi.coin.model.BinanceTradeDto;
import com.enesselvi.coin.service.ParserService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.*;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class CustomWebSocketHandler  implements WebSocketHandler {


    private static final Logger LOGGER   = LoggerFactory.getLogger(CustomWebSocketHandler.class);
    private static final AtomicInteger globalCount= new AtomicInteger(0);

    //Constructor
    private final ParserService parserService;
    private final WebSocketClientBase webSocketClientBase;
    private final String symbol;
    private final WebSocketSessionManager sessionManager;


    public CustomWebSocketHandler(ParserService parserService, WebSocketClientBase webSocketClientBase, String symbol, WebSocketSessionManager sessionManager){
        this.parserService = parserService;
        this.webSocketClientBase = webSocketClientBase;
        this.symbol = symbol;
        this.sessionManager = sessionManager;
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        LOGGER.info("CONNECTION ESTABLISHED WITH SOCKET | sessionId: {}" , session.getId());
        String subscribeJson = webSocketClientBase.getSubscribeJson(symbol);
        session.sendMessage(new TextMessage(subscribeJson));
        LOGGER.info("SUCCESSFULLY SUBSCRIBED THIS SESSION");
        sessionManager.addSession(session.getId() , session);
    }

    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
        LOGGER.info("RECEIVED MESSAGE FROM : {}  | MESSAGE : {}" , session.getId(),message.getPayload());
        String payload = (String) message.getPayload();
        ObjectMapper mapper = new ObjectMapper();
        JsonNode node = mapper.readTree(payload);

        // result null kontrolü
        if (node.has("result") && node.get("result").isNull()) {
            LOGGER.info("Received subscription ACK (result:null), skipping.");
            return;
        }
        // heartbeat kontrolü
        if (node.has("e") && node.get("e").asText().equals("heartbeat")) {
            LOGGER.debug("Heartbeat received, skipping.");
            return;
        }
        BinanceTradeDto trade= parserService.parseJsonToDto(payload);
        globalCount.incrementAndGet();
        LOGGER.info("Binance Trade Data: {}",trade.toString());
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        //LOGGER.error("Exception error on session: {} | Error: {}", session.getId() , exception);
        LOGGER.error(String.format("Exception ocurred while handling | WebSocket SessionId: {}, Error : {}" , session.getId() , exception));
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
        LOGGER.info("DISCONNECTED WITH  SOCKET | sessionId: {}, closeStatus: {}" , session.getId() , closeStatus);
        LOGGER.info("DATA COUNT: {}" , globalCount);
    }

    @Override
    public boolean supportsPartialMessages() {
        return false;
    }
}
