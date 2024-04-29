package com.assessment.chatsystem.adapter.persistence.api;

import com.assessment.chatsystem.adapter.persistence.entity.ChatRoomEntity;
import com.assessment.chatsystem.adapter.persistence.entity.MessageEntity;
import com.assessment.chatsystem.adapter.persistence.mapper.MessageMapper;
import com.assessment.chatsystem.adapter.persistence.repository.ChatRoomRepository;
import com.assessment.chatsystem.adapter.persistence.repository.MessageRepository;
import com.assessment.chatsystem.core.domain.chatDetails.ChatRoomDTO;
import com.assessment.chatsystem.core.domain.chatDetails.MessageDTO;
import nl.altindag.log.LogCaptor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class MessagePersistenceAdapterTest {

    @InjectMocks
    private MessagePersistenceAdapter messagePersistenceAdapter;

    @Mock
    private MessageRepository messageRepository;

    @Mock
    private ChatRoomRepository chatRoomRepository;

    @Mock
    private MessageMapper messageMapper;

    LocalDateTime time = LocalDateTime.now();

    @Test
    public void testFindMessagesByChatRoom() {
        LogCaptor logCaptor = LogCaptor.forClass(MessagePersistenceAdapter.class);
        List<MessageEntity> messageEntities = Collections.singletonList(getMessageEntity());
        List<MessageDTO> expectedResponse =  messageEntities
                .stream()
                .sorted(Comparator.comparing(MessageEntity::getCreatedAt).reversed())
                .map(messageMapper::toDomain)
                .toList();

        when(messageRepository.findAll(any(Specification.class))).thenReturn(messageEntities);
        when(chatRoomRepository.findById(Long.valueOf(getMessageDTO().getChatRoom())))
                .thenReturn(Optional.of(getChatRoomEntity()));

        List<MessageDTO> actualResponse = messagePersistenceAdapter
                .findMessagesByChatRoom(getMessageDTO().getChatRoom());

        assertEquals(expectedResponse,actualResponse);
        assertThat(logCaptor.getInfoLogs()
                .contains("Successfully retrieved messages from chat room"
                        +getChatRoomEntity().getChatRoomName()+"!"));
    }

    @Test
    public void testSendMessage() {
        LogCaptor logCaptor = LogCaptor.forClass(MessagePersistenceAdapter.class);
        MessageDTO expectedResponse = getMessageDTO();
        MessageEntity messageDBResponse = getMessageEntity();

        when(messageMapper.toEntity(getMessageDTO())).thenReturn(getMessageEntity());
        when(chatRoomRepository.findById(Long.valueOf(getMessageDTO().getChatRoom())))
                .thenReturn(Optional.of(getChatRoomEntity()));
        when(messageRepository.saveAndFlush(getMessageEntity())).thenReturn(messageDBResponse);
        when(messageMapper.toDomain(messageDBResponse)).thenReturn(expectedResponse);

        MessageDTO actualResponse = messagePersistenceAdapter.sendMessage(getMessageDTO());

        assertEquals(expectedResponse, actualResponse);
        assertThat(logCaptor.getInfoLogs().contains("Successfully sent message of sender "
                + actualResponse.getSenderName()+"!"));
    }

    private ChatRoomEntity getChatRoomEntity() {
        ChatRoomEntity chatRoomEntity = new ChatRoomEntity();

        chatRoomEntity.setId(1000L);
        chatRoomEntity.setChatRoomName("RC-1000");
        chatRoomEntity.setCreatedBy("user");
        chatRoomEntity.setCreatedAt(time);

        return chatRoomEntity;
    }

    private ChatRoomDTO getChatRoomDTO() {
        ChatRoomDTO chatRoomDTO = new ChatRoomDTO();

        chatRoomDTO.setId(1000L);
        chatRoomDTO.setChatRoomName("RC-1000");
        chatRoomDTO.setCreatedBy("user");
        chatRoomDTO.setCreatedAt(time);

        return chatRoomDTO;
    }

    private MessageEntity getMessageEntity() {
        MessageEntity messageEntity = new MessageEntity();

        messageEntity.setId(1000L);
        messageEntity.setChatRoomId("1000");
        messageEntity.setSenderName("user");
        messageEntity.setMessage("Hello");
        messageEntity.setCreatedBy("user");
        messageEntity.setCreatedAt(time);

        return messageEntity;
    }

    private MessageDTO getMessageDTO() {
        MessageDTO messageDTO = new MessageDTO();

        messageDTO.setChatRoom("1000");
        messageDTO.setSenderName("user");
        messageDTO.setMessage("Hello");
        messageDTO.setCreatedAt(time);

        return messageDTO;
    }

}

