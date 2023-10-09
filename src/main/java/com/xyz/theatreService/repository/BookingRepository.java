package com.xyz.theatreService.repository;

import com.xyz.theatreService.entity.Booking;
import com.xyz.theatreService.entity.Seat;
import com.xyz.theatreService.entity.ShowTime;
import com.xyz.theatreService.entity.Theatre;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BookingRepository extends JpaRepository<Booking, Long> {

    Optional<Booking> findByShowTimeAndSeat(ShowTime showTime, Seat seat);

}
