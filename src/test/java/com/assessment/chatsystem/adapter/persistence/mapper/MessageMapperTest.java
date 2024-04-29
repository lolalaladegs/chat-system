package com.assessment.chatsystem.adapter.persistence.mapper;

import com.assessment.chatsystem.adapter.persistence.entity.ChatRoomEntity;
import com.assessment.chatsystem.adapter.persistence.entity.MessageEntity;
import com.assessment.chatsystem.core.domain.chatDetails.ChatRoomDTO;
import com.assessment.chatsystem.core.domain.chatDetails.MessageDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = MessageMapperTest.MessageMapperTestConfig.class)
public class MessageMapperTest {

    @Configuration
    @ComponentScan(basePackageClasses = {
        MessageMapper.class,
        MessageMapperImpl.class,
        MessageMapperTest.class
    })
    public static class MessageMapperTestConfig {

    }

    @Autowired
    private MessageMapper mapper;

    LocalDateTime time = LocalDateTime.now();

    @Test
    void testToDomain() {
        assertNull(mapper.toDomain(null));

        MessageDTO testDTO = mapper.toDomain(getMessageEntity());
        assertNotNull(testDTO);
        assertEquals("Hello", testDTO.getMessage());
    }

    @Test
    void testToEntity() {
        assertNull(mapper.toEntity(null));

        MessageEntity testEntity = mapper.toEntity(getMessageDTO());
        assertNotNull(testEntity);
        assertEquals("Hello", testEntity.getMessage());
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