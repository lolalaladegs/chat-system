package com.assessment.chatsystem.adapter.persistence.mapper;

import com.assessment.chatsystem.adapter.persistence.entity.ChatRoomEntity;
import com.assessment.chatsystem.core.domain.chatDetails.ChatRoomDTO;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ChatRoomMapper {

    ChatRoomDTO toDomain(ChatRoomEntity entity);

    ChatRoomEntity toEntity(ChatRoomDTO dto);

}
