package com.app.qualfoi.listener;

import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import com.app.qualfoi.model.ChatMessage;
import com.app.qualfoi.model.MessageType;

@Component
public class WSlistener{

    
    private SimpMessageSendingOperations messagingTemplate;

    public WSlistener (SimpMessageSendingOperations messagingTemplate){
        this.messagingTemplate = messagingTemplate;
    }

    @EventListener
    public void handleDisconnect(SessionDisconnectEvent event) {
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());
        String username = (String) headerAccessor.getSessionAttributes().get("username");
        String group = (String) headerAccessor.getSessionAttributes().get("group");

        if (username != null && group != null) {
            ChatMessage leaveMsg = new ChatMessage(MessageType.LEAVE, username, "saiu do grupo", group);
            messagingTemplate.convertAndSend("/topic/" + group, leaveMsg);
        }
    }
}