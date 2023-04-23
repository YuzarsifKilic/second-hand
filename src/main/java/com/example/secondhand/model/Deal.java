package com.example.secondhand.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.joda.time.DateTime;

import java.sql.Date;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Deal {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String message;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
    private String senderId;
    private String receiverId;
    @CreationTimestamp
    private LocalDateTime date;
}
