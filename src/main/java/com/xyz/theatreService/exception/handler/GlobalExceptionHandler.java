package com.xyz.theatreService.exception.handler;

import com.xyz.theatreService.exception.BookingException;
import com.xyz.theatreService.exception.InvalidDataException;
import com.xyz.theatreService.exception.SeatReservedException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Exception formatting for response.
 */
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    /**
     * error handle for @Valid
     */
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatusCode status,
                                                                  WebRequest request) {
        //Get all errors
        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(x -> x.getDefaultMessage())
                .collect(Collectors.toList());

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", new Date());
        body.put("status", status.value());
        body.put("errors", errors);
        body.put("path", ((ServletWebRequest) request).getRequest().getRequestURI().toString());

        return new ResponseEntity<>(body, headers, status);
    }


    @ExceptionHandler({InvalidDataException.class, SeatReservedException.class, BookingException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public @ResponseBody Map<String, Object> handleCustomException(Exception ex, WebRequest request) {
        ex.printStackTrace();
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", new Date());
        body.put("errors", ex.getMessage());
        body.put("status", HttpStatus.BAD_REQUEST.value());
        body.put("path", ((ServletWebRequest) request).getRequest().getRequestURI().toString());
        return body;
    }
}