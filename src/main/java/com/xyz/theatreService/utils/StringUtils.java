package com.xyz.theatreService.utils;

import com.xyz.theatreService.entity.Timings;

public class StringUtils {

    public static final String getShowTimeString(Timings timings) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(timings.getStartTime());
        stringBuilder.append(" - ");
        stringBuilder.append(timings.getEndTime());
        stringBuilder.append(" (");
        stringBuilder.append(timings.getName());
        stringBuilder.append(")");
        return stringBuilder.toString();
    }
}
