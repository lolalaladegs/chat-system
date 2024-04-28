package com.assessment.chatsystem.core.port;

import com.assessment.chatsystem.core.domain.chatDetails.MessageDTO;

import java.util.List;

public interface MessagePort {

    List<MessageDTO> findMessagesByChatRoom(String chatRoomId);

    MessageDTO sendMessage(MessageDTO messageDTO);

}
