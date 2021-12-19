package co.com.daleb.booking.domain.services.impl;

import co.com.daleb.booking.domain.exceptions.BookingException;
import co.com.daleb.booking.domain.jsons.CreateReservationRest;
import co.com.daleb.booking.domain.jsons.ReservationRest;
import co.com.daleb.booking.infraestructure.sql.entities.ReservationEntity;
import co.com.daleb.booking.infraestructure.sql.entities.RestaurantEntity;
import co.com.daleb.booking.infraestructure.sql.entities.TurnEntity;
import co.com.daleb.booking.infraestructure.sql.jpa.ReservationRepository;
import co.com.daleb.booking.infraestructure.sql.jpa.RestaurantRepository;
import co.com.daleb.booking.infraestructure.sql.jpa.TurnRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class ReservationServiceImplTest {


    private static final Long RESTAURANT_ID = 1L;
    private static final String NAME = "Burger ";
    private static final String DESCRIPTION = "Hamburgers";
    private static final String ADDRESS = "Plaza Central";
    private static final String IMAGE = "www.image.com";

    private final static Long RESERVATION_ID = 1L;
    private final static String TURN = "L";
    private final static Long PERSON = 1L;
    private final static LocalDate LOCALDATE = LocalDate.now(ZoneId.of("America/Bogota"));
    private final static String LOCATOR = "Burger 1";

    private static final Long TURN_ID = 1L;

    private static final List<TurnEntity> TURN_LIST = new ArrayList<>();

    private TurnEntity turnEntity = new TurnEntity();

    private ReservationEntity reservationEntity = new ReservationEntity();

    private RestaurantEntity restaurantEntity = new RestaurantEntity();

    private ReservationRest reservationRest = new ReservationRest();

    private CreateReservationRest createReservationRest = new CreateReservationRest();

    private Optional<RestaurantEntity> OPTIONAL_RESTAURANT = Optional.of(restaurantEntity);

    private Optional<TurnEntity> OPTIONAL_TURN = Optional.of(turnEntity);

    @Mock
    ReservationRepository reservationRepository;

    @Mock
    RestaurantRepository restaurantRepository;

    @Mock
    TurnRepository turnRepository;

    @Mock
    ModelMapper modelMapper;

    @InjectMocks
    ReservationServiceImpl reservationServiceImpl;

    @BeforeEach
    public void init() throws BookingException{

        MockitoAnnotations.openMocks(this);

        restaurantEntity.setImage(IMAGE);
        restaurantEntity.setDescription(DESCRIPTION);
        restaurantEntity.setAddress(ADDRESS);
        restaurantEntity.setName(NAME);
        restaurantEntity.setId(RESTAURANT_ID);
        restaurantEntity.setTurns(TURN_LIST);

        createReservationRest.setDate(LOCALDATE);
        createReservationRest.setPerson(PERSON);
        createReservationRest.setRestaurantId(RESTAURANT_ID);
        createReservationRest.setTurnId(TURN_ID);

        turnEntity.setId(TURN_ID);
        turnEntity.setName(NAME);
        turnEntity.setRestaurant(restaurantEntity);

        reservationEntity.setTurn(TURN);
        reservationEntity.setId(RESERVATION_ID);
        reservationEntity.setRestaurant(restaurantEntity);
        reservationEntity.setPerson(PERSON);
        reservationEntity.setDate(LOCALDATE);
        reservationEntity.setLocator(LOCATOR);

        reservationRest.setLocator(LOCATOR);
        reservationRest.setRestaurantId(RESTAURANT_ID.toString());
        reservationRest.setPerson(PERSON);
        reservationRest.setDate(LOCALDATE);
        reservationRest.setTurnId(TURN_ID);


    }

    @Test
    public void getReservationTest() throws BookingException{

        Mockito.when(reservationRepository.findById(RESERVATION_ID)).thenReturn(Optional.of(reservationEntity));

        Mockito.when(modelMapper.map(reservationEntity,ReservationRest.class)).thenReturn(reservationRest);

        ReservationRest response =  reservationServiceImpl.getReservation(RESERVATION_ID);

        assertEquals(response.getLocator(),LOCATOR);

    }

    @Test
    public void setCreateReservationTest() throws BookingException{

        Mockito.when(restaurantRepository.findById(RESTAURANT_ID)).thenReturn(OPTIONAL_RESTAURANT);

        Mockito.when(turnRepository.findById(TURN_ID)).thenReturn(OPTIONAL_TURN);

        String response = reservationServiceImpl.createReservation(createReservationRest);

        assertEquals(response,LOCATOR);

    }

    @Test
    public void deleteReservationTest() throws BookingException{

        Mockito.when(reservationRepository.deleteByLocator(LOCATOR)).thenReturn(Optional.of(reservationEntity));

        String response  = reservationServiceImpl.deleteReservation(LOCATOR);

        assertEquals(response,"LOCATOR_DELETED");

    }

    @Test
    public void deleteReservationErrorTest() throws BookingException{

        Mockito.when(reservationRepository.deleteByLocator(LOCATOR)).thenReturn(Optional.empty());

        assertThrows(BookingException.class,() -> reservationServiceImpl.deleteReservation(LOCATOR));

    }

}
