package com.gl.recyclingservice.service;

import com.gl.recyclingservice.dto.DonationRequestDto;
import com.gl.recyclingservice.dto.DonationResponseDto;
import com.gl.recyclingservice.dto.PointsResponseDto;
import com.gl.recyclingservice.entity.Recycling;
import com.gl.recyclingservice.repository.RecyclingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RecyclingService {

    private final RecyclingRepository recyclingRepository;

    public DonationResponseDto donate(String userEmail, DonationRequestDto request) {

        int points = request.getItemCount() * 10;

        Recycling donation = Recycling.builder()
                .userEmail(userEmail)
                .itemCount(request.getItemCount())
                .description(request.getDescription())
                .pointsEarned(points)
                .build();

        Recycling saved = recyclingRepository.save(donation);

        return mapToDto(saved);
    }

    public PointsResponseDto getPoints(String userEmail) {
        List<Recycling> donations = recyclingRepository.findByUserEmail(userEmail);

        int total = donations.stream()
                .mapToInt(Recycling::getPointsEarned)
                .sum();

        return new PointsResponseDto(userEmail, total);
    }

    public List<DonationResponseDto> getHistory(String userEmail) {
        return recyclingRepository.findByUserEmail(userEmail)
                .stream()
                .map(this::mapToDto)
                .toList();
    }

    private DonationResponseDto mapToDto(Recycling donation) {
        return DonationResponseDto.builder()
                .id(donation.getId())
                .userEmail(donation.getUserEmail())
                .itemCount(donation.getItemCount())
                .description(donation.getDescription())
                .pointsEarned(donation.getPointsEarned())
                .donatedAt(donation.getDonatedAt())
                .build();
    }
}