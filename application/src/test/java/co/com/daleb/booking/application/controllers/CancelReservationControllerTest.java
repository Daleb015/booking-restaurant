package co.com.daleb.booking.application.controllers;

import static org.junit.jupiter.api.Assertions.*;

import co.com.daleb.booking.domain.exceptions.BookingException;
import co.com.daleb.booking.domain.jsons.BookingResponseRest;
import co.com.daleb.booking.domain.services.impl.ReservationServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

class CancelReservationControllerTest {

  private static final String LOCATOR = "Burger 1";
  private static final String RESERVATION_DELETED = "LOCATOR_DELETED";

  private static final String SUCCESS_STATUS = "Success";
  private static final String SUCCESS_CODE = "200 OK";
  private static final String SUCCESS_MESSAGE = "OK";

  @Mock
  ReservationServiceImpl reservationServiceImpl;

  @InjectMocks
  CancelReservationController cancelReservationController;

  @BeforeEach
  public void init() throws BookingException {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  public void deleteReservationTest() throws BookingException {
    Mockito.when(reservationServiceImpl.deleteReservation(LOCATOR)).thenReturn(RESERVATION_DELETED);

    final BookingResponseRest<String> response = cancelReservationController.deleteReservation(LOCATOR);

    assertEquals(response.getMessage(), SUCCESS_MESSAGE);
    assertEquals(response.getCode(), SUCCESS_CODE);
    assertEquals(response.getStatus(), SUCCESS_STATUS);
    assertEquals(response.getData(), RESERVATION_DELETED);
  }
}
