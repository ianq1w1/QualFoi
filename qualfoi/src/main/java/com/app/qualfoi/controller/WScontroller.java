package com.app.qualfoi.controller;

//import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

import com.app.qualfoi.model.ChatMessage;
//import com.app.qualfoi.model.MessageType;
import com.app.qualfoi.service.ChatMessageService;


@Controller
public class WScontroller {

    private ChatMessageService service;

    public WScontroller(ChatMessageService service){
        this.service = service;
    }

    @MessageMapping("/chat/geral/join")
    @SendTo("/topico/geral")
    public ChatMessage join(SimpMessageHeaderAccessor headerAccessor) {
        String nomeAnonimo = service.gerarNome();
        headerAccessor.getSessionAttributes().put("usuario", nomeAnonimo);
        //headerAccessor.getSessionAttributes().put("group", groupId);
        return service.entradaMessage(nomeAnonimo);
       // return new ChatMessage(MessageType.JOIN, nomeAnonimo, "entrou no grupo");
    }

    @MessageMapping("/chat/geral/send")
    @SendTo("/topico/geral")
    public ChatMessage send(ChatMessage message) {
        System.out.println(message);
        return service.enviarMessage(message);
        //return service;
    }
}