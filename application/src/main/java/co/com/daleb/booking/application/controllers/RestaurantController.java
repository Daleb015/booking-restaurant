package co.com.daleb.booking.application.controllers;


import co.com.daleb.booking.domain.exceptions.BookingException;
import co.com.daleb.booking.domain.jsons.BookingResponseRest;
import co.com.daleb.booking.domain.jsons.RestaurantRest;
import co.com.daleb.booking.domain.services.EmailService;
import co.com.daleb.booking.domain.services.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value = "/api/booking-restaurant/" + "v1")
public class RestaurantController {

    private final RestaurantService restaurantService;

    private final EmailService emailService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/restaurant/{restaurantId}",produces = MediaType.APPLICATION_JSON_VALUE)
    public BookingResponseRest<RestaurantRest> getRestaurantById( @PathVariable Long restaurantId) throws BookingException {
        return BookingResponseRest.<RestaurantRest>builder()
                .status("Success")
                .code(String.valueOf(HttpStatus.OK))
                .message("OK")
                .data(restaurantService.getRestaurantById(restaurantId))
                .build();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/restaurants",produces = MediaType.APPLICATION_JSON_VALUE)
    public BookingResponseRest<List<RestaurantRest>> getRestaurants() throws BookingException{
        return BookingResponseRest.<List<RestaurantRest>>builder()
                .status("Success")
                .code(String.valueOf(HttpStatus.OK))
                .message("OK")
                .data(restaurantService.getRestaurants())
                .build();
    }
}
