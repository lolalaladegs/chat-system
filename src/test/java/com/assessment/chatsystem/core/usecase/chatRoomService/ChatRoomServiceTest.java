package com.assessment.chatsystem.core.usecase.chatRoomService;

import com.assessment.chatsystem.core.domain.chatDetails.ChatRoomDTO;
import com.assessment.chatsystem.core.port.ChatRoomPort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class ChatRoomServiceTest {

    @Mock
    private ChatRoomPort chatRoomPort;

    @InjectMocks
    private ChatRoomService chatRoomService;

    public static final String TIME = "2024-04-29 16:35:37";

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Test
    void testFetchChatRooms() {
        List<ChatRoomDTO> expectedResponse = getChatRoomListDTO();
        when(chatRoomPort.fetchChatRooms()).thenReturn(expectedResponse);

        List<ChatRoomDTO> actualResponse = chatRoomService.fetchChatRooms();

        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    @WithMockUser("username")
    void testFetchChatRoomsUponStartup() {
        when(chatRoomPort.fetchChatRooms()).thenReturn(Collections.emptyList());

        List<ChatRoomDTO> actualResponse = chatRoomService.fetchChatRooms();

        assertNotNull(actualResponse);
    }

    private List<ChatRoomDTO> getChatRoomListDTO() {
        ChatRoomDTO chatRoomDTO = new ChatRoomDTO();

        chatRoomDTO.setId(1000L);
        chatRoomDTO.setChatRoomName("RC-1000");
        chatRoomDTO.setCreatedBy("user");
        chatRoomDTO.setCreatedAt(LocalDateTime.parse(TIME, formatter));

        return Collections.singletonList(chatRoomDTO);
    }
}