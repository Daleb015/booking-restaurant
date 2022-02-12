package co.com.daleb.booking.application.controllers;

import static org.junit.jupiter.api.Assertions.*;

import co.com.daleb.booking.domain.exceptions.BookingException;
import co.com.daleb.booking.domain.jsons.BookingResponseRest;
import co.com.daleb.booking.domain.jsons.CreateReservationRest;
import co.com.daleb.booking.domain.jsons.ReservationRest;
import co.com.daleb.booking.domain.services.impl.ReservationServiceImpl;
import java.time.LocalDate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

class ReservationControllerTest {

  private static final String SUCCESS_STATUS = "Success";
  private static final String SUCCESS_CODE = "200 OK";
  private static final String SUCCESS_MESSAGE = "OK";

  private static final LocalDate DATE = LocalDate.now();
  private static final Long PERSON = 1L;
  private static final Long RESTAURANT_ID = 1L;
  private static final Long TURN_ID = 1L;

  private static final Long RESERVATION_ID = 1L;

  private static final String LOCATOR = "BURGER 2";

  private static CreateReservationRest createReservationRest = new CreateReservationRest();

  private static ReservationRest reservationRest = new ReservationRest();

  @Mock
  ReservationServiceImpl reservationServiceImpl;

  @InjectMocks
  ReservationController reservationController;

  @BeforeEach
  public void init() throws BookingException {
    MockitoAnnotations.openMocks(this);

    createReservationRest.setDate(DATE);
    createReservationRest.setPerson(PERSON);
    createReservationRest.setRestaurantId(RESTAURANT_ID);
    createReservationRest.setTurnId(TURN_ID);

    Mockito.when(reservationServiceImpl.createReservation(createReservationRest)).thenReturn(LOCATOR);

    reservationRest.setDate(DATE);
    reservationRest.setPerson(PERSON);
    reservationRest.setTurnId(TURN_ID);
    reservationRest.setRestaurantId(RESTAURANT_ID.toString());
    reservationRest.setLocator(LOCATOR);

    Mockito.when(reservationServiceImpl.getReservation(RESERVATION_ID)).thenReturn(reservationRest);
  }

  @Test
  public void createReservationTest() throws BookingException {
    BookingResponseRest<String> response = reservationController.createReservation(createReservationRest);

    assertEquals(response.getMessage(), SUCCESS_MESSAGE);
    assertEquals(response.getCode(), SUCCESS_CODE);
    assertEquals(response.getStatus(), SUCCESS_STATUS);
    assertEquals(response.getData(), LOCATOR);
  }

  @Test
  public void getReservationByIdTest() throws BookingException {
    BookingResponseRest<ReservationRest> response = reservationController.getReservationById(RESERVATION_ID);

    assertEquals(response.getMessage(), SUCCESS_MESSAGE);
    assertEquals(response.getCode(), SUCCESS_CODE);
    assertEquals(response.getStatus(), SUCCESS_STATUS);
    assertEquals(response.getData(), reservationRest);
  }
}
