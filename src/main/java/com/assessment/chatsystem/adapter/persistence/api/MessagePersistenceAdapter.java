package com.assessment.chatsystem.adapter.persistence.api;

import com.assessment.chatsystem.adapter.persistence.entity.ChatRoomEntity;
import com.assessment.chatsystem.adapter.persistence.entity.MessageEntity;
import com.assessment.chatsystem.adapter.persistence.mapper.MessageMapper;
import com.assessment.chatsystem.adapter.persistence.repository.ChatRoomRepository;
import com.assessment.chatsystem.adapter.persistence.repository.MessageRepository;
import com.assessment.chatsystem.core.domain.chatDetails.MessageDTO;
import com.assessment.chatsystem.core.port.MessagePort;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Component
@Slf4j
@RequiredArgsConstructor
public class MessagePersistenceAdapter implements MessagePort {

    private final MessageRepository messageRepository;

    private final ChatRoomRepository chatRoomRepository;

    private final MessageMapper messageMapper;

    @Override
    public List<MessageDTO> findMessagesByChatRoom(String chatRoomId) {
        List<MessageEntity> messageEntityList = messageRepository.findAll();
        Optional<ChatRoomEntity> chatRoom = chatRoomRepository.findById(Long.valueOf(chatRoomId));

        log.info("Successfully retrieved messages from chat room {}!",
                chatRoom.get().getChatRoomName());

        messageEntityList.forEach(message -> message.setChatRoomId(chatRoom.get().getChatRoomName()));

        return messageEntityList
                .stream()
                .sorted(Comparator.comparing(MessageEntity::getCreatedAt).reversed())
                .map(messageMapper::toDomain)
                .toList();
    }

    @Modifying
    @Transactional
    @Override
    public MessageDTO sendMessage(MessageDTO messageDTO) {
        log.info("Sending message of sender {} to chat room {}...",
                messageDTO.getSenderName(), messageDTO.getChatRoom());

        MessageEntity messageEntity = messageMapper.toEntity(messageDTO);
        Optional<ChatRoomEntity> chatRoom = chatRoomRepository.findById(Long.valueOf(messageDTO.getChatRoom()));
        MessageEntity messageResponse = messageRepository.save(messageEntity);

        log.info("Successfully sent message of sender {}!", messageResponse.getSenderName());

        messageResponse.setChatRoomId(chatRoom.get().getChatRoomName());

        return messageMapper.toDomain(messageResponse);
    }
}
