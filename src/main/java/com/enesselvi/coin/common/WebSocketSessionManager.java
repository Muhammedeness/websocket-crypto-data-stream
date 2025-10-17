package com.enesselvi.coin.common;

import org.springframework.stereotype.Service;
import org.springframework.web.socket.WebSocketSession;

import java.util.concurrent.ConcurrentHashMap;

@Service
public class WebSocketSessionManager {

    private final ConcurrentHashMap<String , WebSocketSession> webSocketSessions = new ConcurrentHashMap<>();

    public void addSession(String sessionId , WebSocketSession session){
        webSocketSessions.put(sessionId , session);
    }

    public void removeSession(String sessionId){
        webSocketSessions.remove(sessionId);
    }

    public WebSocketSession getSession(String sessionId) {
        return webSocketSessions.get(sessionId);
    }

    public boolean isSessionOpen(String sessionId) {
        WebSocketSession session = getSession(sessionId);
        return session != null && session.isOpen();
    }
}
