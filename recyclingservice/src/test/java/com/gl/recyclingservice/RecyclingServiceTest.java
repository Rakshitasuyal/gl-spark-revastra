package com.gl.recyclingservice;

import com.gl.recyclingservice.dto.DonationRequestDto;
import com.gl.recyclingservice.dto.DonationResponseDto;
import com.gl.recyclingservice.dto.PointsResponseDto;
import com.gl.recyclingservice.entity.Recycling;
import com.gl.recyclingservice.repository.RecyclingRepository;
import com.gl.recyclingservice.service.RecyclingService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RecyclingServiceTest {

    @Mock
    private RecyclingRepository recyclingRepository;

    @InjectMocks
    private RecyclingService recyclingService;

    @Test
    void donate_shouldSaveDonationAndReturnResponse() {
        DonationRequestDto request = new DonationRequestDto();
        request.setItemCount(5);
        request.setDescription("Old clothes");

        Recycling saved = Recycling.builder()
                .id(1L)
                .userEmail("rakshita@gmail.com")
                .itemCount(5)
                .description("Old clothes")
                .pointsEarned(50)
                .donatedAt(LocalDateTime.now())
                .build();

        when(recyclingRepository.save(any(Recycling.class))).thenReturn(saved);

        DonationResponseDto response = recyclingService.donate("rakshita@gmail.com", request);

        assertNotNull(response);
        assertEquals(50, response.getPointsEarned());
        assertEquals("rakshita@gmail.com", response.getUserEmail());
    }

    @Test
    void getPoints_shouldReturnTotalPoints() {
        List<Recycling> donations = List.of(
                Recycling.builder().pointsEarned(20).build(),
                Recycling.builder().pointsEarned(30).build()
        );

        when(recyclingRepository.findByUserEmail("rakshita@gmail.com")).thenReturn(donations);

        PointsResponseDto response = recyclingService.getPoints("rakshita@gmail.com");

        assertEquals(50, response.getTotalPoints());
    }

    @Test
    void getHistory_shouldReturnDonationHistory() {
        List<Recycling> donations = List.of(
                Recycling.builder()
                        .id(1L)
                        .userEmail("rakshita@gmail.com")
                        .itemCount(2)
                        .description("Shirts")
                        .pointsEarned(20)
                        .donatedAt(LocalDateTime.now())
                        .build()
        );

        when(recyclingRepository.findByUserEmail("rakshita@gmail.com")).thenReturn(donations);

        List<DonationResponseDto> result = recyclingService.getHistory("rakshita@gmail.com");

        assertEquals(1, result.size());
        assertEquals("Shirts", result.get(0).getDescription());
    }
}