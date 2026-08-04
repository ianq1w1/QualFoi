package com.app.qualfoi.controller;

import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

import com.app.qualfoi.model.ChatMessage;
import com.app.qualfoi.model.MessageType;

@Controller
public class WScontroller {

    @MessageMapping("/chat/{groupId}/join")
    @SendTo("/topic/{groupId}")
    public ChatMessage join(@DestinationVariable String groupId, SimpMessageHeaderAccessor headerAccessor) {

        String nomeAnonimo = "Mano-" + 1;
        headerAccessor.getSessionAttributes().put("username", nomeAnonimo);
        headerAccessor.getSessionAttributes().put("group", groupId);

        return new ChatMessage(MessageType.JOIN, nomeAnonimo, "entrou no grupo", groupId);
    }

    @MessageMapping("/chat/{groupId}/send")
    @SendTo("/topic/{groupId}")
    public ChatMessage send(@DestinationVariable String groupId, ChatMessage message) {
        message.setType(MessageType.CHAT);
        return message;
    }
}