package com.assessment.chatsystem.adapter.web;

import com.assessment.chatsystem.core.domain.chatDetails.ChatRoomDTO;
import com.assessment.chatsystem.core.domain.chatDetails.MessageDTO;
import com.assessment.chatsystem.core.usecase.messageService.MessageUseCase;
import com.assessment.chatsystem.core.usecase.chatRoomService.ChatRoomUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping(value = "/v1/chat",
        produces = {MediaType.APPLICATION_JSON_VALUE})
public class ChatController {

    private final ChatRoomUseCase chatRoomUseCase;

    private final MessageUseCase messageUseCase;

    @GetMapping(value = "/rooms")
    private ResponseEntity<List<ChatRoomDTO>> fetchChatRooms(){
        log.info("Retrieving chat rooms...");

        List<ChatRoomDTO> chatRoomList = chatRoomUseCase.fetchChatRooms();

        return ResponseEntity.ok(chatRoomList);
    }

    @GetMapping(value = "/room/{chatRoomId}")
    private ResponseEntity<List<MessageDTO>> findMessagesByChatRoom(@PathVariable("chatRoomId") final String chatRoomId){
        log.info("Retrieving messages from chat room {}...", chatRoomId);

        List<MessageDTO> messageList = messageUseCase.findMessagesByChatRoom(chatRoomId);

        return ResponseEntity.ok(messageList);
    }

    @PostMapping(value = "/room")
    private ResponseEntity<MessageDTO> sendMessage(@RequestBody final MessageDTO message){
        log.info("Sending message on chat room {}...", message.getChatRoom());

        MessageDTO messageDTO = messageUseCase.sendMessage(message);

        return ResponseEntity.ok(messageDTO);
    }

}