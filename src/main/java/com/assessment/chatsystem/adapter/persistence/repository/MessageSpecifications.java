package com.assessment.chatsystem.adapter.persistence.repository;

import com.assessment.chatsystem.adapter.persistence.entity.MessageEntity;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

public final class MessageSpecifications {

    public static Specification<MessageEntity> hasRoomChatIdEqual(String roomChatId) {
        if (StringUtils.isEmpty(roomChatId)) return null;

        return (root, query, criteriaBuilder) ->
            criteriaBuilder.equal(root.get("roomChatId"), roomChatId);
    }

}