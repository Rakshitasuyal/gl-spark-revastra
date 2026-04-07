package com.gl.recyclingservice.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class DonationRequestDto {

    @NotNull(message = "Item count is required")
    @Min(value = 1, message = "Item count must be at least 1")
    private Integer itemCount;

    @NotBlank(message = "Description is required")
    private String description;
}
