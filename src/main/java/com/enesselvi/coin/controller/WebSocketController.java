package com.enesselvi.coin.controller;

import com.enesselvi.coin.common.WebSocketClientBase;
import com.enesselvi.coin.common.WebSocketClientFactory;
import com.enesselvi.coin.service.WebSocketClientAdapter;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class WebSocketController {

    private final WebSocketClientFactory webSocketClientFactory;


    public WebSocketController(WebSocketClientFactory webSocketClientFactory) {
        this.webSocketClientFactory = webSocketClientFactory;
    }

    @GetMapping("/connect/{platform}/{symbol}")
    public ResponseEntity<String> connect(@PathVariable String platform , @PathVariable String symbol, @RequestParam String uri){

        WebSocketClientBase client = webSocketClientFactory.getClient(platform);
        client.connect(symbol,uri);
        return ResponseEntity.ok().build();

    }

    @GetMapping("/health")
    public ResponseEntity<String> health(){
        return ResponseEntity.ok("ALL IS GOOD");

    }
}
