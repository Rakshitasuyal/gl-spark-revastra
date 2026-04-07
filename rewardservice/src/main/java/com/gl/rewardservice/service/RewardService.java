package com.gl.rewardservice.service;

import com.gl.rewardservice.dto.*;

import java.util.List;

public interface RewardService {
    RewardResponseDto earnPoints(RewardEarnRequestDto requestDto);
    RewardResponseDto redeemPoints(RewardRedeemRequestDto requestDto);
    RewardResponseDto getRewardBalance(Long userId);
    List<RewardTransactionResponseDto> getRewardHistory(Long userId);
}