package com.gl.recyclingservice.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class DonationResponseDto {
    private Long id;
    private String userEmail;
    private Integer itemCount;
    private String description;
    private Integer pointsEarned;
    private LocalDateTime donatedAt;
}