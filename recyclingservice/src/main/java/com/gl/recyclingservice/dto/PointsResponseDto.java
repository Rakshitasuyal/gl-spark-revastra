package com.gl.recyclingservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PointsResponseDto {
    private String userEmail;
    private Integer totalPoints;
}