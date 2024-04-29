package com.assessment.chatsystem.adapter.persistence.entity;

import com.assessment.chatsystem.core.domain.chatDetails.ChatRoomDTO;
import com.assessment.chatsystem.core.domain.chatDetails.MessageDTO;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Random;

import static com.google.code.beanmatchers.BeanMatchers.*;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ChatRoomEntityTest {

    @Test
    public void testAll() {
        registerValueGenerator(() -> {
            Random random = new Random();

            if (random.nextBoolean()) {
                return LocalDateTime.now().plusDays(1);
            }

            return LocalDateTime.now();
        }, LocalDateTime.class);

        assertThat(ChatRoomEntity.class,
                allOf(hasValidBeanConstructor(), hasValidBeanEquals(),
                        hasValidGettersAndSetters(), hasValidBeanHashCode(), hasValidBeanToString()));
    }

    @Test
    public void testHashCode() {
        ChatRoomEntity chatRoomEntity = new ChatRoomEntity();
        chatRoomEntity.hashCode();

        assertNotNull(chatRoomEntity);
    }

    @Test
    void testAllArgsConstructor() {
        LocalDateTime time = LocalDateTime.now();

        ChatRoomEntity chatRoomEntity = new ChatRoomEntity(1000L, "RC-1000", "user", time);

        assertEquals(1000L, chatRoomEntity.getId());
        assertEquals("RC-1000", chatRoomEntity.getChatRoomName());
        assertEquals("user", chatRoomEntity.getCreatedBy());
        assertEquals(time, chatRoomEntity.getCreatedAt());
    }
}