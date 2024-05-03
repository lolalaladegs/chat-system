package com.assessment.chatsystem.core.usecase.chatRoomService;

import com.assessment.chatsystem.core.domain.chatDetails.ChatRoomDTO;
import com.assessment.chatsystem.core.port.ChatRoomPort;
import com.assessment.chatsystem.shared.config.security.SecurityUtility;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ChatRoomService implements ChatRoomUseCase {

    private final ChatRoomPort chatRoomPort;

    @Override
    public List<ChatRoomDTO> fetchChatRooms() {
        List<ChatRoomDTO> chatRoomList = chatRoomPort.fetchChatRooms();

        if (chatRoomList.isEmpty()){
            log.info("No chat room has been found. Will automatically create a chat room...");

            ChatRoomDTO chatRoomDTO = constructChatRoom();
            chatRoomPort.createChatRoom(chatRoomDTO);

            chatRoomList = chatRoomPort.fetchChatRooms();
        }

        return chatRoomList;
    }

    @Override
    public String fetchWSChatRoom() {
        List<ChatRoomDTO> chatRooms = fetchChatRooms();
        log.info("Fetched chatroom groups: {}",chatRooms);

        // GET FIRST CHATROOM
        return chatRooms.get(0).getChatRoomName();
    }

    @Override
    public String fetchWSChatRoomId() {
        List<ChatRoomDTO> chatRooms = fetchChatRooms();
        log.info("Fetched chatroom groups: {}",chatRooms);

        // GET FIRST CHATROOM
        return String.valueOf(chatRooms.get(0).getId());
    }

    private ChatRoomDTO constructChatRoom() {

        ChatRoomDTO chatRoomDTO = new ChatRoomDTO();
        chatRoomDTO.setChatRoomName("RC-" + RandomStringUtils.randomAlphanumeric(10).toUpperCase());
        chatRoomDTO.setCreatedBy(SecurityUtility.getCurrentUserLogin().get());
        chatRoomDTO.setCreatedAt(LocalDateTime.now());

        return chatRoomDTO;
    }

}
