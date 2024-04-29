package com.assessment.chatsystem.adapter.persistence.mapper;

import com.assessment.chatsystem.adapter.persistence.entity.ChatRoomEntity;
import com.assessment.chatsystem.core.domain.chatDetails.ChatRoomDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = ChatRoomMapperTest.ChatRoomMapperTestConfig.class)
public class ChatRoomMapperTest {

    @Configuration
    @ComponentScan(basePackageClasses = {
        ChatRoomMapper.class,
        ChatRoomMapperImpl.class,
        ChatRoomMapperTest.class
    })
    public static class ChatRoomMapperTestConfig {

    }

    @Autowired
    private ChatRoomMapper mapper;

    LocalDateTime time = LocalDateTime.now();

    @Test
    void testToDomain() {
        assertNull(mapper.toDomain(null));

        ChatRoomDTO testDTO = mapper.toDomain(getChatRoomEntity());
        assertNotNull(testDTO);
        assertEquals("RC-1000", testDTO.getChatRoomName());
    }

    @Test
    void testToEntity() {
        assertNull(mapper.toEntity(null));

        ChatRoomEntity testEntity = mapper.toEntity(getChatRoomDTO());
        assertNotNull(testEntity);
        assertEquals("RC-1000", testEntity.getChatRoomName());
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