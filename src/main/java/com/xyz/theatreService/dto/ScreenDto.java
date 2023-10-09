package com.xyz.theatreService.dto;

public final record ScreenDto(Long screenId,
                              String screenName,
                              MovieDto movie) {
}
