package com.assessment.chatsystem.core.domain.model;

import com.assessment.chatsystem.core.domain.chatDetails.MessageType;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChatMessage {
    private String content;
    private String sender;
    private String group;
    private MessageType type;

}
