package com.cdac.entities;

import java.time.LocalDateTime;

import jakarta.annotation.Generated;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "notifications")
@Getter
@Setter
@ToString
public class Notification {
	

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;
    private Long groceryItemId;

    private String message;
    private LocalDateTime sentTime;

    @Enumerated(EnumType.STRING)
    private Status status;

    

}
