package co.com.daleb.booking.domain.services.impl;

import static org.junit.jupiter.api.Assertions.*;

import co.com.daleb.booking.domain.exceptions.BookingException;
import co.com.daleb.booking.domain.exceptions.NotFoundException;
import co.com.daleb.booking.domain.jsons.RestaurantRest;
import co.com.daleb.booking.infraestructure.sql.entities.BoardEntity;
import co.com.daleb.booking.infraestructure.sql.entities.ReservationEntity;
import co.com.daleb.booking.infraestructure.sql.entities.RestaurantEntity;
import co.com.daleb.booking.infraestructure.sql.entities.TurnEntity;
import co.com.daleb.booking.infraestructure.sql.jpa.RestaurantRepository;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

public class RestaurantServiceImplTest {

  private static final RestaurantEntity RESTAURANT_ENTITY = new RestaurantEntity();
  private static final Long RESTAURANT_ID = 1L;
  private static final String NAME = "Burger";
  private static final String DESCRIPTION = "Hamburgers";
  private static final String ADDRESS = "Plaza Central";
  private static final String IMAGE = "www.image.com";

  public static final List<TurnEntity> TURN_ENTITY_LIST = new ArrayList<>();
  public static final List<BoardEntity> BOARD_ENTITY_LIST = new ArrayList<>();
  public static final List<ReservationEntity> RESERVATION_ENTITY_LIST = new ArrayList<>();
  public static final List<RestaurantEntity> RESTAURANT_ENTITY_LIST = new ArrayList<>();

  @Mock
  RestaurantRepository restaurantRepository;

  @Mock
  ModelMapper modelMapper;

  @InjectMocks
  RestaurantServiceImpl restaurantServiceImpl;

  @BeforeEach
  public void init() throws BookingException {
    MockitoAnnotations.openMocks(this);

    RESTAURANT_ENTITY.setName(NAME);
    RESTAURANT_ENTITY.setAddress(ADDRESS);
    RESTAURANT_ENTITY.setDescription(DESCRIPTION);
    RESTAURANT_ENTITY.setId(RESTAURANT_ID);
    RESTAURANT_ENTITY.setImage(IMAGE);
    RESTAURANT_ENTITY.setTurns(TURN_ENTITY_LIST);
    RESTAURANT_ENTITY.setBoards(BOARD_ENTITY_LIST);
    RESTAURANT_ENTITY.setReservations(RESERVATION_ENTITY_LIST);
  }

  @Test
  public void getRestaurantByIdTest() throws BookingException {
    Mockito.when(restaurantRepository.findById(RESTAURANT_ID)).thenReturn(Optional.of(RESTAURANT_ENTITY));

    RestaurantRest restaurantById = restaurantServiceImpl.getRestaurantById(RESTAURANT_ID);
  }

  @Test
  public void getRestaurantByIdTestError() throws BookingException {
    Mockito.when(restaurantRepository.findById(RESTAURANT_ID)).thenReturn(Optional.empty());

    assertThrows(NotFoundException.class, () -> restaurantServiceImpl.getRestaurantById(RESTAURANT_ID));
  }

  @Test
  public void getRestaurantsTest() throws BookingException {
    final RestaurantEntity restaurant = new RestaurantEntity();

    Mockito.when(restaurantRepository.findAll()).thenReturn(Arrays.asList(restaurant));

    final List<RestaurantRest> response = restaurantServiceImpl.getRestaurants();

    assertNotNull(response);
    assertFalse(response.isEmpty());
    assertEquals(response.size(), 1);
  }
}
