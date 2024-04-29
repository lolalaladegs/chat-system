package com.assessment.chatsystem.adapter.persistence.api;

import com.assessment.chatsystem.adapter.persistence.entity.ChatRoomEntity;
import com.assessment.chatsystem.adapter.persistence.entity.MessageEntity;
import com.assessment.chatsystem.adapter.persistence.mapper.ChatRoomMapper;
import com.assessment.chatsystem.adapter.persistence.repository.ChatRoomRepository;
import com.assessment.chatsystem.core.domain.chatDetails.ChatRoomDTO;
import com.assessment.chatsystem.core.domain.chatDetails.MessageDTO;
import com.assessment.chatsystem.core.usecase.messageService.MessageService;
import nl.altindag.log.LogCaptor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.security.InvalidParameterException;
import java.time.LocalDateTime;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
public class ChatRoomPersistenceAdapterTest {

    @InjectMocks
    private ChatRoomPersistenceAdapter chatRoomPersistenceAdapter;

    @Mock
    private ChatRoomRepository chatRoomRepository;

    @Mock
    private ChatRoomMapper chatRoomMapper;

    LocalDateTime time = LocalDateTime.now();

    @Test
    public void testFetchChatRooms() {
        LogCaptor logCaptor = LogCaptor.forClass(ChatRoomPersistenceAdapter.class);
        List<ChatRoomEntity> chatRoomEntities = Collections.singletonList(getChatRoomEntity());
        List<ChatRoomDTO> expectedResponse =  chatRoomEntities
                .stream()
                .sorted(Comparator.comparing(ChatRoomEntity::getCreatedAt).reversed())
                .map(chatRoomMapper::toDomain)
                .toList();

        when(chatRoomRepository.findAll()).thenReturn(Collections.singletonList(getChatRoomEntity()));

        List<ChatRoomDTO> actualResponse = chatRoomPersistenceAdapter
                .fetchChatRooms();

        assertEquals(expectedResponse,actualResponse);
        assertThat(logCaptor.getInfoLogs().contains("Successfully retrieved chat room list!"));
    }

    @Test
    public void testCreateChatRoom() {
        LogCaptor logCaptor = LogCaptor.forClass(ChatRoomPersistenceAdapter.class);

        ChatRoomDTO expectedResponse = getChatRoomDTO();
        ChatRoomEntity chatRoomDBResponse = getChatRoomEntity();

        when(chatRoomMapper.toEntity(getChatRoomDTO())).thenReturn(getChatRoomEntity());
        when(chatRoomRepository.save(getChatRoomEntity())).thenReturn(chatRoomDBResponse);
        when(chatRoomMapper.toDomain(getChatRoomEntity())).thenReturn(expectedResponse);

        ChatRoomDTO actualResponse = chatRoomPersistenceAdapter.createChatRoom(getChatRoomDTO());

        assertEquals(expectedResponse, actualResponse);
        assertThat(logCaptor.getInfoLogs().contains("Successfully created chat room "+ actualResponse.getChatRoomName()));
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

}

