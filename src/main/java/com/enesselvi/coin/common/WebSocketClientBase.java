package com.enesselvi.coin.common;

import java.util.concurrent.CompletableFuture;

public abstract class WebSocketClientBase{
    public abstract CompletableFuture<String> connect(String symbol , String uri);
    public abstract void disconnect(String sessionId , String symbol);
    public abstract String getSubscribeJson(String symbol);
    public abstract String getUnsubscribeJson(String symbol0);
}
