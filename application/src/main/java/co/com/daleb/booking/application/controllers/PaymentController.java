package co.com.daleb.booking.application.controllers;

import co.com.daleb.booking.domain.exceptions.BookingException;
import co.com.daleb.booking.domain.jsons.PaymentConfirmRequestRest;
import co.com.daleb.booking.domain.jsons.PaymentIntentRequestRest;
import co.com.daleb.booking.domain.services.PaymentService;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(path = "/api/booking-restaurant/v1/payment")
public class PaymentController {

    private final PaymentService paymentService;


    @PostMapping(value = "/intent", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> paymentIntent(@RequestBody PaymentIntentRequestRest paymentIntentRequestRest) throws StripeException {

        PaymentIntent paymentIntent = paymentService.paymentIntent(paymentIntentRequestRest);

        return new ResponseEntity<String>(paymentIntent.toJson(), HttpStatus.OK);

    }

    @PostMapping(value = "/confirm", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> paymentIntent(@RequestBody PaymentConfirmRequestRest paymentConfirmRequestRest) throws StripeException, BookingException {

        PaymentIntent paymentIntent = paymentService.paymentConfirm(paymentConfirmRequestRest);

        return new ResponseEntity<String>(paymentIntent.toJson(), HttpStatus.OK);

    }

    @GetMapping(value = "/cancel/{paymentId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> paymentIntent(@PathVariable String paymentId) throws StripeException {

        PaymentIntent paymentIntent = paymentService.paymentCancel(paymentId);

        return new ResponseEntity<String>(paymentIntent.toJson(), HttpStatus.OK);

    }

}
