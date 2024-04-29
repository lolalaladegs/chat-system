package com.assessment.chatsystem.core.usecase.messageService;

import com.assessment.chatsystem.core.domain.chatDetails.MessageDTO;
import com.assessment.chatsystem.core.port.MessagePort;
import nl.altindag.log.LogCaptor;
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

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class MessageServiceTest {

    @Mock
    private MessagePort messagePort;

    @InjectMocks
    private MessageService messageService;

    public static final String TIME = "2024-04-29 16:35:37";

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Test
    void testFindMessagesByChatRoom() {
        List<MessageDTO> expectedResponse = Collections.singletonList(getMessageDTO());

        when(messagePort.findMessagesByChatRoom(getMessageDTO().getChatRoom()))
                .thenReturn(expectedResponse);

        List<MessageDTO> actualResponse = messageService
                .findMessagesByChatRoom(getMessageDTO().getChatRoom());

        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    void testFindMessagesByChatRoomWithEmptyMessage() {
        LogCaptor logCaptor = LogCaptor.forClass(MessageService.class);
        List<MessageDTO> message = Collections.singletonList(getMessageDTO());

        when(messagePort.findMessagesByChatRoom(getMessageDTO().getChatRoom()))
                .thenReturn(Collections.emptyList());

        messageService.findMessagesByChatRoom(getMessageDTO().getChatRoom());

        assertThat(logCaptor.getInfoLogs().contains("No messages in chat room " + message.get(0).getChatRoom()));
    }

    @Test
    @WithMockUser("username")
    void testSendMessage() {
        MessageDTO expectedResponse = getMessageDTO();

        when(messagePort.sendMessage(getMessageRequest()))
                .thenReturn(expectedResponse);

        MessageDTO actualResponse = messageService
                .sendMessage(getMessageDTO());

        assertThat(actualResponse);
    }

    @Test
    void testSendMessageWithNullRequest() {
        MessageDTO actualResponse = messageService
                .sendMessage(null);

        assertNull(actualResponse);
    }

    private MessageDTO getMessageDTO() {
        MessageDTO messageDTO = new MessageDTO();

        messageDTO.setChatRoom("1000");
        messageDTO.setSenderName("username");
        messageDTO.setMessage("Hello");
        messageDTO.setCreatedAt(LocalDateTime.parse(TIME, formatter));

        return messageDTO;
    }

    private MessageDTO getMessageRequest() {
        MessageDTO messageDTO = new MessageDTO();

        messageDTO.setSenderName("username");
        messageDTO.setMessage("Hello");

        return messageDTO;
    }
}