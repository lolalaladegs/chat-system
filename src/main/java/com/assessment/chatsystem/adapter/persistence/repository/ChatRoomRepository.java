package com.assessment.chatsystem.adapter.persistence.repository;

import com.assessment.chatsystem.adapter.persistence.entity.ChatRoomEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatRoomRepository extends JpaRepository<ChatRoomEntity, Long>,
        JpaSpecificationExecutor<ChatRoomEntity> {

}
