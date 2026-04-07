package com.gl.recyclingservice.repository;

import com.gl.recyclingservice.entity.Recycling;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecyclingRepository extends JpaRepository<Recycling, Long> {

    List<Recycling> findByUserEmail(String userEmail);
}