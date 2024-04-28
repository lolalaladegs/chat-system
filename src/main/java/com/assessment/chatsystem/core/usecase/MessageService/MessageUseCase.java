package com.assessment.chatsystem.core.usecase.MessageService;

import com.assessment.chatsystem.core.domain.chatDetails.MessageDTO;

import java.util.List;

public interface MessageUseCase {

    List<MessageDTO> findMessagesByChatRoom(String chatRoomId);

    MessageDTO sendMessage(MessageDTO messageDTO);

}
