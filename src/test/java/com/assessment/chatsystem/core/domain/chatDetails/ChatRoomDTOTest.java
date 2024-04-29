package com.assessment.chatsystem.core.domain.chatDetails;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Random;

import static com.google.code.beanmatchers.BeanMatchers.*;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ChatRoomDTOTest {

    @Test
    public void testAll() {
        assertThat(ChatRoomDTO.class,
                allOf(hasValidBeanConstructor(), hasValidBeanEquals(),
                        hasValidGettersAndSetters(), hasValidBeanHashCode(), hasValidBeanToString()));
    }

    @Test
    public void testHashCode() {
        ChatRoomDTO chatRoomDTO = new ChatRoomDTO();
        chatRoomDTO.hashCode();

        assertNotNull(chatRoomDTO);
    }

}