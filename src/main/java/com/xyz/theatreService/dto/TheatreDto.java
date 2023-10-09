package com.xyz.theatreService.dto;

import java.util.List;
import java.util.Objects;

public final record TheatreDto(Long theatreId,
                               String theatreName,
                               String cityName,
                               List<ScreenDto> screens) {

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TheatreDto that = (TheatreDto) o;
        return Objects.equals(theatreId, that.theatreId) && Objects.equals(theatreName, that.theatreName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(theatreId, theatreName);
    }
}
