package co.com.daleb.booking.domain.services;

import co.com.daleb.booking.domain.exceptions.BookingException;
import co.com.daleb.booking.domain.jsons.CreateReservationRest;
import co.com.daleb.booking.domain.jsons.ReservationRest;

public interface ReservationService {

    ReservationRest getReservation(Long reservationId) throws BookingException;

    String deleteReservation(String locator) throws BookingException;

    String createReservation(CreateReservationRest createReservationRest) throws BookingException;

}
