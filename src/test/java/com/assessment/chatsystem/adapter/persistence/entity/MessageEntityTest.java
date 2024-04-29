package com.assessment.chatsystem.adapter.persistence.entity;

import com.assessment.chatsystem.core.domain.chatDetails.MessageDTO;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Random;

import static com.google.code.beanmatchers.BeanMatchers.*;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class MessageEntityTest {

    @Test
    public void testAll() {
        registerValueGenerator(() -> {
            Random random = new Random();

            if (random.nextBoolean()) {
                return LocalDateTime.now().plusDays(1);
            }

            return LocalDateTime.now();
        }, LocalDateTime.class);

        assertThat(MessageEntity.class,
                allOf(hasValidBeanConstructor(), hasValidBeanEquals(),
                        hasValidGettersAndSetters(), hasValidBeanHashCode(), hasValidBeanToString()));
    }

    @Test
    public void testHashCode() {
        MessageEntity messageEntity = new MessageEntity();
        messageEntity.hashCode();

        assertNotNull(messageEntity);
    }

    @Test
    void testAllArgsConstructor() {
        LocalDateTime time = LocalDateTime.now();

        MessageEntity messageEntity = new MessageEntity(1000L,"1000", "user", "Hello","user", time);

        assertEquals(1000L, messageEntity.getId());
        assertEquals("1000", messageEntity.getChatRoomId());
        assertEquals("user", messageEntity.getSenderName());
        assertEquals("Hello", messageEntity.getMessage());
        assertEquals("user", messageEntity.getCreatedBy());
        assertEquals(time, messageEntity.getCreatedAt());
    }
}