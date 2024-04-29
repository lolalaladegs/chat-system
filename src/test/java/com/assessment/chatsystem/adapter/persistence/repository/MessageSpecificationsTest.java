package com.assessment.chatsystem.adapter.persistence.repository;

import com.assessment.chatsystem.adapter.persistence.entity.MessageEntity;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

@ExtendWith(SpringExtension.class)
public class MessageSpecificationsTest {

    @Mock
    private CriteriaBuilder criteriaBuilder;

    @Mock
    private CriteriaQuery<MessageEntity> query;

    @Mock
    private Predicate predicate;

    @Mock
    private Root<MessageEntity> root;

    @InjectMocks
    MessageSpecifications messageSpecifications;

    @Test
    public void testHasRoomChatIdEqualWithNull() {
        Specification<MessageEntity> spec = MessageSpecifications.hasRoomChatIdEqual(null);

        assertNull(spec);
    }

    @Test
    public void testHasRoomChatIdEqualWithEmpty() {
        Specification<MessageEntity> spec = MessageSpecifications.hasRoomChatIdEqual("");

        assertNull(spec);
    }

    @Test
    public void testHasRoomChatIdEqualWithNonNullCriteriaBuilder() {
        String roomChatId = "1000";
        Specification<MessageEntity> specification = MessageSpecifications.hasRoomChatIdEqual(roomChatId);

        predicate = specification.toPredicate(root, query, criteriaBuilder);
        assertEquals(criteriaBuilder.equal(root.get("chatRoomId"), roomChatId),predicate);
    }
}