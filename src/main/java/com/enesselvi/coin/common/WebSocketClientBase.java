package com.enesselvi.coin.common;

public abstract class WebSocketClientBase{
    public abstract void connect(String symbol , String uri);
    public abstract String getSubscribeJson(String symbol);
    //public abstract void  subscribe(String symbol , String uri);
}
