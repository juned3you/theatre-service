package com.xyz.theatreService.repository;

import com.xyz.theatreService.entity.Seat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeatRepository extends JpaRepository<Seat, Long> {
}
