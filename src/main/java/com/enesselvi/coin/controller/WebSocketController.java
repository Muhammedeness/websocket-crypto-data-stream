package com.enesselvi.coin.controller;

import com.enesselvi.coin.common.WebSocketClientBase;
import com.enesselvi.coin.common.WebSocketClientFactory;
import com.enesselvi.coin.model.ConnectionRequestDto;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/v1/ws/stream")
@RestController
public class WebSocketController {

    private final WebSocketClientFactory webSocketClientFactory;


    public WebSocketController(WebSocketClientFactory webSocketClientFactory) {
        this.webSocketClientFactory = webSocketClientFactory;
    }

    @PostMapping("/connect")
    public ResponseEntity<String> connect(@Valid @RequestBody ConnectionRequestDto requestDto){

        WebSocketClientBase client = webSocketClientFactory.getClient(requestDto.getPlatform());
        client.connect(requestDto.getSymbol(),requestDto.getUri());
        return ResponseEntity.ok().build();

    }

    @GetMapping("/health")
    public ResponseEntity<String> health(){
        return ResponseEntity.ok("ALL IS GOOD");

    }
}
