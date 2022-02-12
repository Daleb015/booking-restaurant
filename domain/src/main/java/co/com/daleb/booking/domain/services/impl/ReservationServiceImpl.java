package co.com.daleb.booking.domain.services.impl;

import co.com.daleb.booking.domain.exceptions.BookingException;
import co.com.daleb.booking.domain.exceptions.InternalServerError;
import co.com.daleb.booking.domain.exceptions.NotFoundException;
import co.com.daleb.booking.domain.jsons.CreateReservationRest;
import co.com.daleb.booking.domain.jsons.ReservationRest;
import co.com.daleb.booking.domain.services.EmailService;
import co.com.daleb.booking.domain.services.ReservationService;
import co.com.daleb.booking.infraestructure.sql.entities.ReservationEntity;
import co.com.daleb.booking.infraestructure.sql.entities.RestaurantEntity;
import co.com.daleb.booking.infraestructure.sql.entities.TurnEntity;
import co.com.daleb.booking.infraestructure.sql.jpa.ReservationRepository;
import co.com.daleb.booking.infraestructure.sql.jpa.RestaurantRepository;
import co.com.daleb.booking.infraestructure.sql.jpa.TurnRepository;
import java.util.Optional;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Data
@Service
public class ReservationServiceImpl implements ReservationService {

  private final RestaurantRepository restaurantRepository;

  private final TurnRepository turnRepository;

  private final ReservationRepository reservationRepository;

  private final ModelMapper modelMapper;

  private final EmailService emailService;

  @Override
  public ReservationRest getReservation(Long reservationId) throws BookingException {
    ReservationEntity reservationEntity = getReservationEntityById(reservationId);

    ReservationRest reservationRest = modelMapper.map(reservationEntity, ReservationRest.class);

    return reservationRest;
  }

  @Override
  public String deleteReservation(String locator) throws BookingException {
    ReservationEntity reservationEntity = reservationRepository
      .findByLocator(locator)
      .orElseThrow(() -> new NotFoundException("LOCATOR_NOT_FOUND", "LOCATOR_NOT_FOUND"));

    reservationRepository.deleteByLocator(locator);

    emailService.sendEmail(reservationEntity.getEmail(), "CANCEL", reservationEntity.getName());

    return "LOCATOR_DELETED";
  }

  @Override
  public String createReservation(CreateReservationRest createReservationRest) throws BookingException {
    final RestaurantEntity restaurant = getRestaurantEntityById(createReservationRest.getRestaurantId());

    ReservationEntity reservationEntity = ReservationEntity
      .builder()
      .locator(generateLocator(restaurant, createReservationRest))
      .person(createReservationRest.getPerson())
      .date(createReservationRest.getDate())
      .restaurant(restaurant)
      .email(createReservationRest.getEmail())
      .name(createReservationRest.getName())
      .turn(getTurnEntityById(createReservationRest.getTurnId()).getName())
      .build();

    save(reservationEntity);

    emailService.sendEmail(reservationEntity.getEmail(), "RESERVATION", reservationEntity.getName());

    return reservationEntity.getLocator();
  }

  @Override
  public void changePaymentReservation(String locator) throws BookingException {
    Optional
      .ofNullable(locator)
      .flatMap(reservationRepository::findByLocator)
      .map(r -> {
        r.setPayment(true);
        return r;
      })
      .map(reservationRepository::save)
      .orElseThrow(() -> new NotFoundException("LOCATOR_NOT_FOUND", "LOCATOR_NOT_FOUND"));
  }

  public ReservationEntity getReservationEntityById(Long reservationId) throws BookingException {
    return reservationRepository
      .findById(reservationId)
      .orElseThrow(() -> new NotFoundException("SNOT-404-1", "RESERVATION_NOT_FOUND"));
  }

  private RestaurantEntity getRestaurantEntityById(Long restaurantId) throws BookingException {
    return restaurantRepository
      .findById(restaurantId)
      .orElseThrow(() -> new NotFoundException("RESTAURANT_NOT_FOUND", "RESTAURANT_NOT_FOUND"));
  }

  private TurnEntity getTurnEntityById(Long turnId) throws BookingException {
    return turnRepository.findById(turnId).orElseThrow(() -> new NotFoundException("TURN_NOT_FOUND", "TURN_NOT_FOUND"));
  }

  public String generateLocator(RestaurantEntity restaurantEntity, CreateReservationRest createReservationRest) {
    return restaurantEntity.getName() + createReservationRest.getTurnId();
  }

  private ReservationEntity save(ReservationEntity reservationEntity) throws BookingException {
    try {
      return reservationRepository.save(reservationEntity);
    } catch (Exception e) {
      throw new InternalServerError("ERROR_PERSIST_DATA", e.getMessage());
    }
  }
}
