package com.app.qualfoi.service;

import org.springframework.stereotype.Service;

import com.app.qualfoi.model.ChatMessage;
import com.app.qualfoi.model.MessageType;

@Service
public class ChatMessageService {
    
    public String gerarNome(){
       return "Mano-" + 1;
    }

    public ChatMessage entradaMessage(String nomeAnonimo){
        return new ChatMessage(MessageType.JOIN, nomeAnonimo, "entrou no grupo");
    }

    public ChatMessage enviarMessage(ChatMessage message){
        message.setType(MessageType.CHAT);
        return message;
    }
}