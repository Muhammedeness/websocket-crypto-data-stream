package com.enesselvi.coin.controller;

import com.enesselvi.coin.service.CustomWebSocketClient;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WebSocketController {

    private final CustomWebSocketClient customWebSocketClient;

    public WebSocketController(CustomWebSocketClient customWebSocketClient) {
        this.customWebSocketClient = customWebSocketClient;
    }

    @GetMapping("/connect/{symbol}")
    public ResponseEntity<String> connect(@PathVariable String symbol){
        customWebSocketClient.connect(symbol);
        return ResponseEntity.ok().build();

    }

    @GetMapping("/health")
    public ResponseEntity<String> health(){
        return ResponseEntity.ok("ALL IS GOOD");

    }
}
