package com.xyz.theatreService.service.impl;

import com.xyz.theatreService.dto.MovieDto;
import com.xyz.theatreService.dto.ScreenDto;
import com.xyz.theatreService.dto.TheatreDto;
import com.xyz.theatreService.entity.Movie;
import com.xyz.theatreService.entity.Screen;
import com.xyz.theatreService.entity.ShowTime;
import com.xyz.theatreService.entity.Theatre;
import com.xyz.theatreService.exception.SearchTheatreException;
import com.xyz.theatreService.repository.ShowTimeRepository;
import com.xyz.theatreService.service.TheatreService;
import com.xyz.theatreService.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Theatre Operations Service.
 */
@Service
public final class TheatreServiceImpl implements TheatreService {

    private static final Logger logger = LoggerFactory.getLogger(TheatreServiceImpl.class);

    private final ShowTimeRepository showTimeRepository;

    public TheatreServiceImpl(@Autowired ShowTimeRepository showTimeRepository) {
        this.showTimeRepository = showTimeRepository;
    }

    @Override
    public Set<TheatreDto> getTheatreByShowDate(Date showDate, Integer pageNumber, Integer pageSize, String sortBy)
            throws SearchTheatreException {
        try {
            Pageable paging = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy));
            Page<ShowTime> pagedResult = showTimeRepository.findByShowDate(showDate, paging);

            if (pagedResult.hasContent()) {
                List<ShowTime> showtimesList = pagedResult.getContent();
                logger.info("getTheatreByShowDate called: Result size: {}", showtimesList.size());
                return transferResponse(showtimesList);
            } else {
                logger.info("getTheatreByShowDate called: No results");
            }
        } catch (Exception exception) {
            throw new SearchTheatreException(exception.getMessage(), exception);
        }

        return Collections.EMPTY_SET;
    }

    private Set<TheatreDto> transferResponse(List<ShowTime> showtimesList) {
        final Set<TheatreDto> uniqueTheatre = showtimesList.stream().map(showTime -> {
            Theatre theatre = showTime.getScreen().getTheatre();
            return new TheatreDto(theatre.getId(), theatre.getName(), theatre.getCity().getName(), new ArrayList<>());
        }).collect(Collectors.toSet());

        Set<TheatreDto> theatreList = showtimesList.stream().map(showTime -> {
            Theatre theatre = showTime.getScreen().getTheatre();
            TheatreDto theatreDto = uniqueTheatre.stream().filter(th -> th.theatreId().equals(theatre.getId()))
                    .findFirst().get();
            return convertShowTime(showTime, theatreDto);
        }).collect(Collectors.toSet());
        return theatreList;
    }

    private TheatreDto convertShowTime(ShowTime showTime, TheatreDto theatreDto) {
        Movie movie = showTime.getMovie();
        MovieDto movieDto = new MovieDto(movie.getId(),
                movie.getName(),
                movie.getDuration(),
                movie.getDescription(),
                movie.getGenre().getName(),
                movie.getLanguage().getName(),
                StringUtils.getShowTimeString(showTime.getTimings()),
                showTime.getTicketPrice());

        Screen screen = showTime.getScreen();
        ScreenDto screenDto = new ScreenDto(screen.getId(), screen.getName(), movieDto);
        theatreDto.screens().add(screenDto);
        return theatreDto;
    }


}
