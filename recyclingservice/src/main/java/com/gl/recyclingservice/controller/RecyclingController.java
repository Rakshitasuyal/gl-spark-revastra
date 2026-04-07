package com.gl.recyclingservice.controller;

import com.gl.recyclingservice.dto.DonationRequestDto;
import com.gl.recyclingservice.dto.DonationResponseDto;
import com.gl.recyclingservice.dto.PointsResponseDto;
import com.gl.recyclingservice.service.RecyclingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/recycling")
@RequiredArgsConstructor
public class RecyclingController {

    private final RecyclingService recyclingService;

    @PostMapping("/donate")
    public ResponseEntity<DonationResponseDto> donate(Authentication authentication,
                                                      @Valid @RequestBody DonationRequestDto request) {
        return ResponseEntity.ok(recyclingService.donate(authentication.getName(), request));
    }

    @GetMapping("/points")
    public ResponseEntity<PointsResponseDto> getPoints(Authentication authentication) {
        return ResponseEntity.ok(recyclingService.getPoints(authentication.getName()));
    }

    @GetMapping("/history")
    public ResponseEntity<List<DonationResponseDto>> getHistory(Authentication authentication) {
        return ResponseEntity.ok(recyclingService.getHistory(authentication.getName()));
    }
}