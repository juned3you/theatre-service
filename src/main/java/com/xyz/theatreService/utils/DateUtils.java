package com.xyz.theatreService.utils;

import java.util.Date;

public class DateUtils {

    public static final Date formatDate(Date date) {
        return new Date(date.getYear(), date.getMonth(), date.getDate());
    }
}
