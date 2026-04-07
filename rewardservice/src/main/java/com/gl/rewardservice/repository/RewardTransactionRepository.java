package com.gl.rewardservice.repository;

import com.gl.rewardservice.entity.RewardTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RewardTransactionRepository extends JpaRepository<RewardTransaction, Long> {
    List<RewardTransaction> findByUserIdOrderByCreatedAtDesc(Long userId);
}
