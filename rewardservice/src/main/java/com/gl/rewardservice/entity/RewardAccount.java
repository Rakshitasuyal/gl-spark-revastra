package com.gl.rewardservice.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "reward_account")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RewardAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", nullable = false, unique = true)
    private Long userId;

    @Column(name = "total_points_earned", nullable = false)
    private Integer totalPointsEarned;

    @Column(name = "total_points_redeemed", nullable = false)
    private Integer totalPointsRedeemed;

    @Column(name = "balance_points", nullable = false)
    private Integer balancePoints;

    @Column(name = "last_updated", nullable = false)
    private LocalDateTime lastUpdated;
}