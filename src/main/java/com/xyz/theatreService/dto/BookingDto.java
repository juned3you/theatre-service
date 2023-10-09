package com.xyz.theatreService.dto;

public record BookingDto(Long bookingId, String message, String status, String showTime, String screen) {
}
