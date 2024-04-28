package com.assessment.chatsystem.adapter.persistence.mapper;

import com.assessment.chatsystem.adapter.persistence.entity.ChatRoomEntity;
import com.assessment.chatsystem.core.domain.chatDetails.ChatRoomDTO;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-04-28T15:47:34+0400",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.1 (Homebrew)"
)
@Component
public class ChatRoomMapperImpl implements ChatRoomMapper {

    @Override
    public ChatRoomDTO toDomain(ChatRoomEntity entity) {
        if ( entity == null ) {
            return null;
        }

        ChatRoomDTO chatRoomDTO = new ChatRoomDTO();

        chatRoomDTO.setChatRoomName( entity.getChatRoomName() );
        chatRoomDTO.setCreatedBy( entity.getCreatedBy() );
        chatRoomDTO.setCreatedAt( entity.getCreatedAt() );

        return chatRoomDTO;
    }

    @Override
    public ChatRoomEntity toEntity(ChatRoomDTO dto) {
        if ( dto == null ) {
            return null;
        }

        ChatRoomEntity chatRoomEntity = new ChatRoomEntity();

        chatRoomEntity.setChatRoomName( dto.getChatRoomName() );
        chatRoomEntity.setCreatedBy( dto.getCreatedBy() );
        chatRoomEntity.setCreatedAt( dto.getCreatedAt() );

        return chatRoomEntity;
    }
}
