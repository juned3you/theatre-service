package com.xyz.theatreService.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.xyz.theatreService.utils.Constants;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class BookingCriteria {

    @Min(1)
    private long theatreId;

    @Min(1)
    private long screenId;

    @Min(1)
    private long timingsId;

    @Min(1)
    private long seatId;

    @NotNull
    @JsonFormat(pattern = Constants.DATE_FORMAT)
    private Date showDate;

    @Override
    public String toString() {
        return "BookingCriteria{" +
                "theatreId=" + theatreId +
                ", screenId=" + screenId +
                ", timingsId=" + timingsId +
                ", seatId=" + seatId +
                ", showDate=" + showDate +
                '}';
    }
}
