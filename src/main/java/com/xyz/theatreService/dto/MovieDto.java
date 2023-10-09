package com.xyz.theatreService.dto;

public final record MovieDto(Long movieId,
                             String movieName,
                             String duration,
                             String description,
                             String genre,
                             String language,
                             String showTimings,
                             Float ticketPrice) {
}
