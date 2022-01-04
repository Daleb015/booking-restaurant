package co.com.daleb.booking.application.controllers;

import co.com.daleb.booking.domain.exceptions.BookingException;
import co.com.daleb.booking.domain.jsons.BookingResponseRest;
import co.com.daleb.booking.domain.services.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(path = "/api/booking-restaurant/v1")
public class CancelReservationController {

    private final ReservationService reservationService;

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping(value = "reservation",produces = MediaType.APPLICATION_JSON_VALUE)
    public BookingResponseRest<String> deleteReservation(@RequestParam String locator) throws BookingException {
        return BookingResponseRest.<String>builder()
                .status("Success")
                .message("OK")
                .code(String.valueOf(HttpStatus.OK))
                .data(reservationService.deleteReservation(locator))
                .build();
    }

}
