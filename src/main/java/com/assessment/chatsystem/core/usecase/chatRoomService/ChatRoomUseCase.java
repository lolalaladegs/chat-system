package com.assessment.chatsystem.core.usecase.chatRoomService;

import com.assessment.chatsystem.core.domain.chatDetails.ChatRoomDTO;

import java.util.List;

public interface ChatRoomUseCase {

    List<ChatRoomDTO> fetchChatRooms();

    String fetchWSChatRoom();

    String fetchWSChatRoomId();

}
