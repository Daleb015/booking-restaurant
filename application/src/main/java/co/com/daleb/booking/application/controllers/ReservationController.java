package co.com.daleb.booking.application.controllers;

import co.com.daleb.booking.domain.exceptions.BookingException;
import co.com.daleb.booking.domain.jsons.BookingResponseRest;
import co.com.daleb.booking.domain.jsons.CreateReservationRest;
import co.com.daleb.booking.domain.jsons.ReservationRest;
import co.com.daleb.booking.domain.services.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/api/booking-restaurant/v1")
public class ReservationController {

    private final ReservationService reservationService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(path = "/reservation/{reservationId}")
    public BookingResponseRest<ReservationRest> reservationById(@PathVariable Long reservationId) throws BookingException {
        return BookingResponseRest.<ReservationRest>builder()
                .status("Success")
                .message("OK")
                .code(String.valueOf(HttpStatus.OK))
                .data(reservationService.getReservation(reservationId))
                .build();
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping(path = "/reservation",produces = MediaType.APPLICATION_JSON_VALUE)
    public BookingResponseRest<String> createReservation(@RequestBody CreateReservationRest createReservationRest) throws BookingException{
        return BookingResponseRest.<String>builder()
                .status("Success")
                .code(String.valueOf(HttpStatus.OK))
                .message("OK")
                .data(reservationService.createReservation(createReservationRest))
                .build();
    }

}
