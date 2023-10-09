package com.xyz.theatreService.service;

import com.xyz.theatreService.exception.SearchTheatreException;
import com.xyz.theatreService.dto.TheatreDto;

import java.util.Date;
import java.util.Set;

public interface TheatreService {

    Set<TheatreDto> getTheatreByShowDate(Date showDate, Integer pageNumber, Integer pageSize, String sortBy)
            throws SearchTheatreException;
}
