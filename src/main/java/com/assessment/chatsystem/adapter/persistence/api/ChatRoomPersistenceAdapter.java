package com.assessment.chatsystem.adapter.persistence.api;

import com.assessment.chatsystem.adapter.persistence.entity.ChatRoomEntity;
import com.assessment.chatsystem.adapter.persistence.mapper.ChatRoomMapper;
import com.assessment.chatsystem.adapter.persistence.repository.ChatRoomRepository;
import com.assessment.chatsystem.core.domain.chatDetails.ChatRoomDTO;
import com.assessment.chatsystem.core.port.ChatRoomPort;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;

@Component
@Slf4j
@RequiredArgsConstructor
public class ChatRoomPersistenceAdapter implements ChatRoomPort {

    private final ChatRoomRepository chatRoomRepository;

    private final ChatRoomMapper chatRoomMapper;

    @Override
    public List<ChatRoomDTO> fetchChatRooms() {
        List<ChatRoomEntity> chatRoomEntityList = chatRoomRepository.findAll();

        log.info("Successfully retrieved chat room list!");

        return chatRoomEntityList
                .stream()
                .sorted(Comparator.comparing(ChatRoomEntity::getCreatedAt).reversed())
                .map(chatRoomMapper::toDomain)
                .toList();
    }

    @Modifying
    @Transactional
    @Override
    public ChatRoomDTO createChatRoom(ChatRoomDTO chatRoomDTO) {
        log.info("Creating chat room {}...", chatRoomDTO.getChatRoomName());

        ChatRoomEntity chatRoomEntity = chatRoomMapper.toEntity(chatRoomDTO);
        ChatRoomEntity chatRoomResponse = chatRoomRepository.save(chatRoomEntity);

        log.info("Successfully created chat room {}!", chatRoomResponse.getChatRoomName());

        return chatRoomMapper.toDomain(chatRoomResponse);
    }

}
