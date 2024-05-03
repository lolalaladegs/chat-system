package com.assessment.chatsystem.adapter.web;

import com.assessment.chatsystem.core.domain.chatDetails.MessageDTO;
import com.assessment.chatsystem.core.domain.model.ChatMessage;
import com.assessment.chatsystem.core.usecase.chatRoomService.ChatRoomUseCase;
import com.assessment.chatsystem.core.usecase.messageService.MessageUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ChatWSController {

    private final ChatRoomUseCase chatRoomUseCase;

    private final MessageUseCase messageUseCase;

    @MessageMapping("/chat.send")
    @SendTo("/topic/public")
    public ChatMessage sendMessage(@Payload ChatMessage message){
        messageUseCase.sendMessage(constructMessage(message));
        return message;
    }

    @MessageMapping("/chat.register")
    @SendTo("/topic/public")
    public ChatMessage register(@Payload ChatMessage message, SimpMessageHeaderAccessor headerAccessor){
        headerAccessor.getSessionAttributes().put("username", message.getSender());
        return message;
    }

    @MessageMapping("/chat.group")
    @SendTo("/topic/group")
    public ChatMessage group(@Payload ChatMessage message){
        return ChatMessage.builder()
                .group(chatRoomUseCase.fetchWSChatRoom())
                .build();
    }

    private MessageDTO constructMessage(ChatMessage message){
        MessageDTO messageDTO = new MessageDTO();
        messageDTO.setMessage(message.getContent());
        messageDTO.setChatRoom(chatRoomUseCase.fetchWSChatRoomId());
        messageDTO.setSenderName(message.getSender());
        return  messageDTO;
    }

}