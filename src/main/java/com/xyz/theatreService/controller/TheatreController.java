package com.xyz.theatreService.controller;

import com.xyz.theatreService.dto.TheatreDto;
import com.xyz.theatreService.exception.SearchTheatreException;
import com.xyz.theatreService.service.TheatreService;
import com.xyz.theatreService.utils.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.Set;

/**
 * Theartre operations controller.
 */
@RestController
@RequestMapping("/theatres")
public class TheatreController {

    private static final Logger logger = LoggerFactory.getLogger(TheatreController.class);
    private static final String CACHE_NAME = "theatreSearchResults";

    private final TheatreService theatreService;

    public TheatreController(@Autowired TheatreService theatreService) {
        this.theatreService = theatreService;
    }

    /**
     * Search show times by date.
     *
     * @param showDate   Date show time date
     * @param pageNumber
     * @param pageSize
     * @param sortBy
     * @return
     * @throws SearchTheatreException
     */
    @GetMapping("/search")
    @Cacheable(CACHE_NAME)
    public Set<TheatreDto> searchTheatreByDate(@RequestParam @DateTimeFormat(pattern = Constants.DATE_FORMAT) Date showDate,
                                               @RequestParam(defaultValue = "0") Integer pageNumber,
                                               @RequestParam(defaultValue = "10") Integer pageSize,
                                               @RequestParam(defaultValue = "id") String sortBy) throws SearchTheatreException {
        logger.info("searchTheatreByDate called with showDate: {}", showDate);
        return theatreService.getTheatreByShowDate(showDate, pageNumber, pageSize, sortBy);
    }

}
