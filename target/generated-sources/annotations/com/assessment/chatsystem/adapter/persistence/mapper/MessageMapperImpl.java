package com.assessment.chatsystem.adapter.persistence.mapper;

import com.assessment.chatsystem.adapter.persistence.entity.MessageEntity;
import com.assessment.chatsystem.core.domain.chatDetails.MessageDTO;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-04-28T15:47:34+0400",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.1 (Homebrew)"
)
@Component
public class MessageMapperImpl implements MessageMapper {

    @Override
    public MessageDTO toDomain(MessageEntity entity) {
        if ( entity == null ) {
            return null;
        }

        MessageDTO messageDTO = new MessageDTO();

        messageDTO.setChatRoom( entity.getChatRoomId() );
        messageDTO.setSenderName( entity.getSenderName() );
        messageDTO.setMessage( entity.getMessage() );
        messageDTO.setCreatedAt( entity.getCreatedAt() );

        return messageDTO;
    }

    @Override
    public MessageEntity toEntity(MessageDTO dto) {
        if ( dto == null ) {
            return null;
        }

        MessageEntity messageEntity = new MessageEntity();

        messageEntity.setCreatedBy( dto.getSenderName() );
        messageEntity.setSenderName( dto.getSenderName() );
        messageEntity.setMessage( dto.getMessage() );
        messageEntity.setCreatedAt( dto.getCreatedAt() );

        return messageEntity;
    }
}
