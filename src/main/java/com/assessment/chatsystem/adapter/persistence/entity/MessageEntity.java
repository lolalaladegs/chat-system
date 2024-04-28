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
@Table(name = "message")
public class MessageEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = -1518556788659249110L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "message_generator")
    @SequenceGenerator(name = "message_generator", sequenceName = "message_sequence", allocationSize = 1)
    private Long id;

    @Column(name = "chat_room_id", length = 255)
    private String chatRoomId;

    @Column(name = "sender_name", length = 255)
    private String senderName;

    @Column(name = "message", length = 255)
    private String message;

    @Column(name = "created_by", length = 255)
    private String createdBy;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

}