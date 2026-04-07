package com.gl.rewardservice.service;

import com.gl.rewardservice.dto.*;
import com.gl.rewardservice.entity.*;
import com.gl.rewardservice.exception.InsufficientPointsException;
import com.gl.rewardservice.repository.RewardAccountRepository;
import com.gl.rewardservice.repository.RewardTransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class RewardServiceImpl implements RewardService {

    private final RewardAccountRepository rewardAccountRepository;
    private final RewardTransactionRepository rewardTransactionRepository;

    @Override
    public RewardResponseDto earnPoints(RewardEarnRequestDto requestDto) {
        RewardAccount account = rewardAccountRepository.findByUserId(requestDto.getUserId())
                .orElse(RewardAccount.builder()
                        .userId(requestDto.getUserId())
                        .totalPointsEarned(0)
                        .totalPointsRedeemed(0)
                        .balancePoints(0)
                        .lastUpdated(LocalDateTime.now())
                        .build());

        account.setTotalPointsEarned(account.getTotalPointsEarned() + requestDto.getPoints());
        account.setBalancePoints(account.getBalancePoints() + requestDto.getPoints());
        account.setLastUpdated(LocalDateTime.now());

        RewardAccount savedAccount = rewardAccountRepository.save(account);

        RewardTransaction transaction = RewardTransaction.builder()
                .userId(requestDto.getUserId())
                .points(requestDto.getPoints())
                .transactionType(RewardTransactionType.EARN)
                .source(requestDto.getSource())
                .description(requestDto.getDescription())
                .createdAt(LocalDateTime.now())
                .build();

        rewardTransactionRepository.save(transaction);

        return mapToResponse(savedAccount);
    }

    @Override
    public RewardResponseDto redeemPoints(RewardRedeemRequestDto requestDto) {
        RewardAccount account = rewardAccountRepository.findByUserId(requestDto.getUserId())
                .orElseThrow(() -> new InsufficientPointsException("Reward account not found for user"));

        if (account.getBalancePoints() < requestDto.getPoints()) {
            throw new InsufficientPointsException("Insufficient reward points");
        }

        account.setTotalPointsRedeemed(account.getTotalPointsRedeemed() + requestDto.getPoints());
        account.setBalancePoints(account.getBalancePoints() - requestDto.getPoints());
        account.setLastUpdated(LocalDateTime.now());

        RewardAccount savedAccount = rewardAccountRepository.save(account);

        RewardTransaction transaction = RewardTransaction.builder()
                .userId(requestDto.getUserId())
                .points(requestDto.getPoints())
                .transactionType(RewardTransactionType.REDEEM)
                .source(RewardSource.PURCHASE)
                .description(requestDto.getDescription())
                .createdAt(LocalDateTime.now())
                .build();

        rewardTransactionRepository.save(transaction);

        return mapToResponse(savedAccount);
    }

    @Override
    @Transactional(readOnly = true)
    public RewardResponseDto getRewardBalance(Long userId) {
        RewardAccount account = rewardAccountRepository.findByUserId(userId)
                .orElse(RewardAccount.builder()
                        .userId(userId)
                        .totalPointsEarned(0)
                        .totalPointsRedeemed(0)
                        .balancePoints(0)
                        .lastUpdated(LocalDateTime.now())
                        .build());

        return mapToResponse(account);
    }

    @Override
    @Transactional(readOnly = true)
    public List<RewardTransactionResponseDto> getRewardHistory(Long userId) {
        return rewardTransactionRepository.findByUserIdOrderByCreatedAtDesc(userId)
                .stream()
                .map(this::mapToTransactionResponse)
                .toList();
    }

    private RewardResponseDto mapToResponse(RewardAccount account) {
        return RewardResponseDto.builder()
                .userId(account.getUserId())
                .totalPointsEarned(account.getTotalPointsEarned())
                .totalPointsRedeemed(account.getTotalPointsRedeemed())
                .balancePoints(account.getBalancePoints())
                .build();
    }

    private RewardTransactionResponseDto mapToTransactionResponse(RewardTransaction transaction) {
        return RewardTransactionResponseDto.builder()
                .id(transaction.getId())
                .userId(transaction.getUserId())
                .points(transaction.getPoints())
                .transactionType(transaction.getTransactionType())
                .source(transaction.getSource())
                .description(transaction.getDescription())
                .createdAt(transaction.getCreatedAt())
                .build();
    }
}