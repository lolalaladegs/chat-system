package com.assessment.chatsystem.adapter.web;

import com.assessment.chatsystem.core.domain.chatDetails.ChatRoomDTO;
import com.assessment.chatsystem.core.domain.chatDetails.MessageDTO;
import com.assessment.chatsystem.core.usecase.messageService.MessageUseCase;
import com.assessment.chatsystem.core.usecase.chatRoomService.ChatRoomUseCase;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(value = ChatController.class)
@AutoConfigureMockMvc(addFilters = false)
public class ChatControllerTest {

    public static final String TIME = "2024-04-29 16:35:37";

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @MockBean
    private ChatRoomUseCase chatRoomUseCase;

    @MockBean
    private MessageUseCase messageUseCase;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testFetchChatRooms() throws Exception {

        ChatRoomDTO responseDto = getChatRoomDTO();

        Mockito.when(chatRoomUseCase.fetchChatRooms())
            .thenReturn(Collections.singletonList(responseDto));

        mockMvc.perform(get("/v1/chat/rooms")
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[0].id").value(responseDto.getId()))
            .andExpect(jsonPath("$[0].chat_room_name").value(responseDto.getChatRoomName()))
            .andExpect(jsonPath("$[0].created_by").value(responseDto.getCreatedBy()))
            .andExpect(jsonPath("$[0].created_at").value(TIME));
    }

    @Test
    void testFindMessagesByChatRoom() throws Exception {

        MessageDTO responseDto = getMessageDTO();

        Mockito.when(messageUseCase.findMessagesByChatRoom("1000"))
                .thenReturn(Collections.singletonList(responseDto));

        mockMvc.perform(get("/v1/chat/room/{chatRoomId}","1000")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].chat_room").value(responseDto.getChatRoom()))
                .andExpect(jsonPath("$[0].sender_name").value(responseDto.getSenderName()))
                .andExpect(jsonPath("$[0].message").value(responseDto.getMessage()))
                .andExpect(jsonPath("$[0].created_at").value(TIME));
    }

    @Test
    void testSendMessage() throws Exception {

        MessageDTO responseDto = getMessageDTO();

        Mockito.when(messageUseCase.sendMessage(getMessageDTO()))
                .thenReturn(responseDto);

        mockMvc.perform(post("/v1/chat/room")
                        .content(objectMapper.writeValueAsString(responseDto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.chat_room").value(responseDto.getChatRoom()))
                .andExpect(jsonPath("$.sender_name").value(responseDto.getSenderName()))
                .andExpect(jsonPath("$.message").value(responseDto.getMessage()))
                .andExpect(jsonPath("$.created_at").value(TIME));
    }

    private ChatRoomDTO getChatRoomDTO() {
        ChatRoomDTO chatRoomDTO = new ChatRoomDTO();

        chatRoomDTO.setId(1000L);
        chatRoomDTO.setChatRoomName("RC-1000");
        chatRoomDTO.setCreatedBy("user");
        chatRoomDTO.setCreatedAt(LocalDateTime.parse(TIME, formatter));

        return chatRoomDTO;
    }

    private MessageDTO getMessageDTO() {
        MessageDTO messageDTO = new MessageDTO();

        messageDTO.setChatRoom("1000");
        messageDTO.setSenderName("user");
        messageDTO.setMessage("Hello");
        messageDTO.setCreatedAt(LocalDateTime.parse(TIME, formatter));

        return messageDTO;
    }
}