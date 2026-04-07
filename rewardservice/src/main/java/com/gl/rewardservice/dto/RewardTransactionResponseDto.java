package com.gl.rewardservice.dto;

import com.gl.rewardservice.entity.RewardSource;
import com.gl.rewardservice.entity.RewardTransactionType;
import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RewardTransactionResponseDto {
    private Long id;
    private Long userId;
    private Integer points;
    private RewardTransactionType transactionType;
    private RewardSource source;
    private String description;
    private LocalDateTime createdAt;
}