package com.assessment.chatsystem.core.port;

import com.assessment.chatsystem.core.domain.chatDetails.ChatRoomDTO;

import java.util.List;

public interface ChatRoomPort {

    List<ChatRoomDTO> fetchChatRooms();

    ChatRoomDTO createChatRoom(ChatRoomDTO chatRoomDTO);

}
