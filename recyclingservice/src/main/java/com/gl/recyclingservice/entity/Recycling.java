package com.gl.recyclingservice.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "recycling")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Recycling {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String userEmail;

    @Column(nullable = false)
    private Integer itemCount;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private Integer pointsEarned;

    @Column(nullable = false)
    private LocalDateTime donatedAt;

    @PrePersist
    public void prePersist() {
        this.donatedAt = LocalDateTime.now();
    }
}
