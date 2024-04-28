package com.assessment.chatsystem.adapter.persistence.repository;

import com.assessment.chatsystem.adapter.persistence.entity.MessageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<MessageEntity, Long>,
        JpaSpecificationExecutor<MessageEntity> {

}
