package com.gl.rewardservice.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RewardResponseDto {
    private Long userId;
    private Integer totalPointsEarned;
    private Integer totalPointsRedeemed;
    private Integer balancePoints;
}
