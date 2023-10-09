package com.xyz.theatreService.repository;

import com.xyz.theatreService.entity.Screen;
import com.xyz.theatreService.entity.ShowTime;
import com.xyz.theatreService.entity.Timings;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Date;
import java.util.Optional;

/**
 * ShowTime Repository.
 */
public interface ShowTimeRepository extends PagingAndSortingRepository<ShowTime, Long> {

    /**
     * Method to get all show times by show date.
     *
     * @param showDate Date
     * @param pageable Pageable
     * @return List<ShowTime>
     */
    Page<ShowTime> findByShowDate(Date showDate, Pageable pageable);

    Optional<ShowTime> findByScreenAndTimingsAndShowDate(Screen screen, Timings timings, Date showDate);
}
