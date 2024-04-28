package com.assessment.chatsystem.adapter.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "chat_room")
public class ChatRoomEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = -1518556788659249110L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "chat_room_generator")
    @SequenceGenerator(name = "chat_room_generator", sequenceName = "chat_room_sequence", allocationSize = 1)
    private Long id;

    @Column(name = "chat_room_name", length = 255)
    private String chatRoomName;

    @Column(name = "created_by", length = 255)
    private String createdBy;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

}