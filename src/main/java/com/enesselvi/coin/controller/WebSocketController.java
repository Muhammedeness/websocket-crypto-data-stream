package com.enesselvi.coin.controller;

import com.enesselvi.coin.common.WebSocketClientBase;
import com.enesselvi.coin.common.WebSocketClientFactory;
import com.enesselvi.coin.model.ConnectionRequestDto;
import com.enesselvi.coin.model.DisconnectRequestDto;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;

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
         CompletableFuture<String> sessionIdFuture= client.connect(requestDto.getSymbol(),requestDto.getUri());
         String sessionId = sessionIdFuture.join();
        return ResponseEntity.ok(sessionId);
    }


    @PostMapping("/disconnect")
    public ResponseEntity<String> disconnect(@RequestBody DisconnectRequestDto requestDto){
        WebSocketClientBase client = webSocketClientFactory.getClient(requestDto.getPlatform());
        client.disconnect(requestDto.getSessionId() , requestDto.getSymbol());
        return ResponseEntity.ok().build();
    }

    @GetMapping("/health")
    public ResponseEntity<String> health(){
        return ResponseEntity.ok("ALL IS GOOD");

    }
}
