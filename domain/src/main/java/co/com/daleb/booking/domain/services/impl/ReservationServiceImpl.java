package co.com.daleb.booking.domain.services.impl;

import co.com.daleb.booking.domain.exceptions.BookingException;
import co.com.daleb.booking.domain.exceptions.InternalServerError;
import co.com.daleb.booking.domain.exceptions.NotFoundException;
import co.com.daleb.booking.domain.jsons.CreateReservationRest;
import co.com.daleb.booking.domain.jsons.ReservationRest;
import co.com.daleb.booking.domain.services.ReservationService;
import co.com.daleb.booking.infraestructure.sql.entities.ReservationEntity;
import co.com.daleb.booking.infraestructure.sql.entities.RestaurantEntity;
import co.com.daleb.booking.infraestructure.sql.entities.TurnEntity;
import co.com.daleb.booking.infraestructure.sql.jpa.ReservationRepository;
import co.com.daleb.booking.infraestructure.sql.jpa.RestaurantRepository;
import co.com.daleb.booking.infraestructure.sql.jpa.TurnRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Data
@Service
public class ReservationServiceImpl implements ReservationService {

    private final RestaurantRepository restaurantRepository;

    private final TurnRepository turnRepository;

    private final ReservationRepository reservationRepository;

    private final ModelMapper modelMapper;

    @Override
    public ReservationRest getReservation(Long reservationId) throws BookingException {
        return modelMapper.map(getReservationEntityById(reservationId), ReservationRest.class);
    }

    @Override
    public String deleteReservation(String locator) throws BookingException {
        reservationRepository.deleteByLocator(locator)
                .orElseThrow(() -> new NotFoundException("LOCATOR_NOT_FOUND","LOCATOR_NOT_FOUND"));

        return "LOCATOR_DELETED";
    }

    @Override
    public String createReservation(CreateReservationRest createReservationRest) throws BookingException {

        final RestaurantEntity restaurant = getRestaurantEntityById(createReservationRest.getRestaurantId());

        return save(ReservationEntity.builder()
                .locator(generateLocator(restaurant, createReservationRest))
                .person(createReservationRest.getPerson())
                .date(createReservationRest.getDate())
                .restaurant(restaurant)
                .turn(getTurnEntityById(createReservationRest.getTurnId()).getName())
                .build()).getLocator();
    }

    private ReservationEntity getReservationEntityById(Long reservationId) throws BookingException {
        return reservationRepository.findById(reservationId)
                .orElseThrow(() -> new NotFoundException("SNOT-404-1", "RESERVATION_NOT_FOUND"));
    }

    private RestaurantEntity getRestaurantEntityById(Long restaurantId) throws BookingException{
        return restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new NotFoundException("RESTAURANT_NOT_FOUND","RESTAURANT_NOT_FOUND"));
    }

    private TurnEntity getTurnEntityById(Long turnId) throws BookingException{
        return turnRepository.findById(turnId)
                .orElseThrow(() -> new NotFoundException("TURN_NOT_FOUND","TURN_NOT_FOUND"));
    }

    private String generateLocator(RestaurantEntity restaurantEntity, CreateReservationRest createReservationRest) {
        return restaurantEntity.getName() + createReservationRest.getTurnId();
    }

    private ReservationEntity save(ReservationEntity reservationEntity) throws BookingException{
        try{
            return reservationRepository.save(reservationEntity);
        }catch(Exception e){
            throw new InternalServerError("ERROR_PERSIST_DATA",e.getMessage());
        }
    }

}
