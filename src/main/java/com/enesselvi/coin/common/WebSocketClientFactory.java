package com.enesselvi.coin.common;

import com.enesselvi.coin.service.impl.BinanceWebSocketClientImpl;
import org.springframework.stereotype.Component;

@Component
public class WebSocketClientFactory {

    private final BinanceWebSocketClientImpl binanceClient;

    public WebSocketClientFactory(BinanceWebSocketClientImpl binanceClient) {
        this.binanceClient = binanceClient;
    }

    public WebSocketClientBase getClient(String platform) {
        return switch (platform.toLowerCase()) {
            case "binance" -> binanceClient;
            default -> throw new IllegalArgumentException("Unsupported platform: " + platform);
        };
    }
}
