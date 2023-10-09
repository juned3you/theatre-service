package com.xyz.theatreService.repository;

import com.xyz.theatreService.entity.Timings;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TimingsRepository extends JpaRepository<Timings, Long> {
}
