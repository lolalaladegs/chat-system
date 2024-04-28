package com.assessment.chatsystem.adapter.persistence.mapper;

import com.assessment.chatsystem.adapter.persistence.entity.MessageEntity;
import com.assessment.chatsystem.core.domain.chatDetails.MessageDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface MessageMapper {

    @Mapping(target = "chatRoom", source = "chatRoomId")
    MessageDTO toDomain(MessageEntity entity);


    @Mapping(target = "createdBy", source = "senderName")
    MessageEntity toEntity(MessageDTO dto);

}
