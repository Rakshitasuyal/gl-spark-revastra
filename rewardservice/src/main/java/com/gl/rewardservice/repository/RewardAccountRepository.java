package com.gl.rewardservice.repository;

import com.gl.rewardservice.entity.RewardAccount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RewardAccountRepository extends JpaRepository<RewardAccount, Long> {
    Optional<RewardAccount> findByUserId(Long userId);
}
