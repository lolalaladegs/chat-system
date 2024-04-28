package com.assessment.chatsystem.core.usecase.MessageService;

import com.assessment.chatsystem.core.domain.chatDetails.MessageDTO;
import com.assessment.chatsystem.core.port.MessagePort;
import com.assessment.chatsystem.shared.config.security.SecurityUtility;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Service
@Slf4j
@RequiredArgsConstructor
public class MessageService implements MessageUseCase {

    private final MessagePort messagePort;

    @Override
    public List<MessageDTO> findMessagesByChatRoom(String chatRoomId) {
        List<MessageDTO> messageList = messagePort.findMessagesByChatRoom(chatRoomId);

        if (messageList.isEmpty()) {
            log.info("No messages in chat room {}", chatRoomId);
        }

        return messageList;
    }

    @Override
    public MessageDTO sendMessage(MessageDTO messageDTO) {
        if (Objects.isNull(messageDTO)) return null;
        return messagePort.sendMessage(constructMessage(messageDTO));
    }

    private MessageDTO constructMessage(MessageDTO messageDTO) {
        messageDTO.setSenderName(SecurityUtility.getCurrentUserLogin().get());
        messageDTO.setCreatedAt(LocalDateTime.now());

        return messageDTO;
    }

}