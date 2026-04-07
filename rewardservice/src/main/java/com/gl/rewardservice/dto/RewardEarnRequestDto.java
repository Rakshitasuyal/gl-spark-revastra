package com.gl.rewardservice.dto;

import com.gl.rewardservice.entity.RewardSource;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RewardEarnRequestDto {

    @NotNull(message = "User id is required")
    private Long userId;

    @NotNull(message = "Points are required")
    @Min(value = 1, message = "Points must be at least 1")
    private Integer points;

    @NotNull(message = "Source is required")
    private RewardSource source;

    private String description;
}