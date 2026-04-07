package com.gl.rewardservice.controller;

import com.gl.rewardservice.dto.*;
import com.gl.rewardservice.service.RewardService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rewards")
@RequiredArgsConstructor
public class RewardController {

    private final RewardService rewardService;

    @PostMapping("/earn")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public ResponseEntity<RewardResponseDto> earnPoints(@Valid @RequestBody RewardEarnRequestDto requestDto) {
        return new ResponseEntity<>(rewardService.earnPoints(requestDto), HttpStatus.CREATED);
    }

    @PostMapping("/redeem")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<RewardResponseDto> redeemPoints(@Valid @RequestBody RewardRedeemRequestDto requestDto) {
        return ResponseEntity.ok(rewardService.redeemPoints(requestDto));
    }

    @GetMapping("/{userId}")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<RewardResponseDto> getRewardBalance(@PathVariable Long userId) {
        return ResponseEntity.ok(rewardService.getRewardBalance(userId));
    }

    @GetMapping("/history/{userId}")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<List<RewardTransactionResponseDto>> getRewardHistory(@PathVariable Long userId) {
        return ResponseEntity.ok(rewardService.getRewardHistory(userId));
    }
}