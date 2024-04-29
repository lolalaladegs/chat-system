package com.assessment.chatsystem.core.domain.chatDetails;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Random;

import static com.google.code.beanmatchers.BeanMatchers.*;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

public class MessageDTOTest {

    @Test
    public void testAll() {
        registerValueGenerator(() -> {
            Random random = new Random();

            if (random.nextBoolean()) {
                return LocalDateTime.now().plusDays(1);
            }

            return LocalDateTime.now();
        }, LocalDateTime.class);

        assertThat(MessageDTO.class,
                allOf(hasValidBeanConstructor(), hasValidBeanEquals(),
                        hasValidGettersAndSetters(), hasValidBeanHashCode(), hasValidBeanToString()));
    }

    @Test
    public void testHashCode() {
        MessageDTO messageDTO = new MessageDTO();
        messageDTO.hashCode();

        assertNotNull(messageDTO);
    }

    @Test
    void testAllArgsConstructor() {
        LocalDateTime time = LocalDateTime.now();

        MessageDTO messageDTO = new MessageDTO("RC-1000", "user", "Hello", time);

        assertEquals("RC-1000", messageDTO.getChatRoom());
        assertEquals("user", messageDTO.getSenderName());
        assertEquals("Hello", messageDTO.getMessage());
        assertEquals(time, messageDTO.getCreatedAt());
    }

}