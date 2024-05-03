package com.assessment.chatsystem.core.domain.model;

import com.assessment.chatsystem.core.domain.chatDetails.ChatRoomDTO;
import com.assessment.chatsystem.core.domain.chatDetails.MessageDTO;
import com.assessment.chatsystem.core.domain.chatDetails.MessageType;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static com.google.code.beanmatchers.BeanMatchers.*;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ChatMessageTest {

    @Test
    public void testAll() {
        assertThat(ChatMessage.class,
                allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
    }

    @Test
    void testAllArgsConstructor() {
        ChatMessage message = new ChatMessage("Hello","user","RC-1000", MessageType.CHAT);

        assertEquals("RC-1000", message.getGroup());
        assertEquals("user", message.getSender());
        assertEquals("Hello", message.getContent());
        assertEquals(MessageType.CHAT, message.getType());
    }

    @Test
    void testBuilder() {
        ChatMessage message = ChatMessage.builder()
                .sender("user")
                .content("Hello")
                .type(MessageType.CHAT)
                .group("RC-1000")
                .build();

        assertEquals("RC-1000", message.getGroup());
        assertEquals("user", message.getSender());
        assertEquals("Hello", message.getContent());
        assertEquals(MessageType.CHAT, message.getType());
    }

}