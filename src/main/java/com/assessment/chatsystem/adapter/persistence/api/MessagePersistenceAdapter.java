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
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import static com.assessment.chatsystem.adapter.persistence.repository.MessageSpecifications.hasRoomChatIdEqual;

@Component
@Slf4j
@RequiredArgsConstructor
public class MessagePersistenceAdapter implements MessagePort {

    private final MessageRepository messageRepository;

    private final ChatRoomRepository chatRoomRepository;

    private final MessageMapper messageMapper;

    @Override
    public List<MessageDTO> findMessagesByChatRoom(String chatRoomId) {
        Specification<MessageEntity> spec = buildSpecification(chatRoomId);
        List<MessageEntity> messageEntityList = messageRepository.findAll(spec);

        log.info("Successfully retrieved messages from chat room {}!", chatRoomId);

        if (!messageEntityList.isEmpty()) {
            Optional<ChatRoomEntity> chatRoom = chatRoomRepository.findById(Long.valueOf(chatRoomId));

            messageEntityList.forEach(message -> message.setChatRoomId(chatRoom.get().getChatRoomName()));
        }

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
        MessageEntity messageDbResponse = messageRepository.saveAndFlush(messageEntity);

        log.info("Successfully sent message of sender {}!", messageDbResponse.getSenderName());

        MessageDTO messageResponse =  messageMapper.toDomain(messageDbResponse);
        messageResponse.setChatRoom(chatRoom.get().getChatRoomName());

        return messageResponse;
    }


    private Specification<MessageEntity> buildSpecification(String request) {

        Specification<MessageEntity> specification = Specification.where(null);

        if (StringUtils.isNotBlank(request)) {
            specification = specification.and(hasRoomChatIdEqual(request));
        }

        return specification;
    }
}
