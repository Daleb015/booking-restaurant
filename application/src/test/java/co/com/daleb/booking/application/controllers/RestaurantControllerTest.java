package co.com.daleb.booking.application.controllers;

import co.com.daleb.booking.domain.exceptions.BookingException;
import co.com.daleb.booking.domain.jsons.BookingResponseRest;
import co.com.daleb.booking.domain.jsons.RestaurantRest;
import co.com.daleb.booking.domain.jsons.TurnRest;
import co.com.daleb.booking.domain.services.RestaurantService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RestaurantControllerTest {

    private static final Long RESTAURANT_ID = 1L;
    private static final String NAME = "Burger";
    private static final String DESCRIPTION = "Hamburgers";
    private static final String ADDRESS = "Plaza Central";
    private static final String IMAGE = "www.image.com";

    private static final String SUCCESS_STATUS = "Success";
    private static final String SUCCESS_CODE = "200 OK";
    private static final String SUCCESS_MESSAGE = "OK";

    public static final RestaurantRest RESTAURANT_REST = RestaurantRest.builder().build();
    public static final List<TurnRest> TURN_LIST = new ArrayList<>();
    public static final List<RestaurantRest> RESTAURANT_REST_LIST = new ArrayList<>();



    @Mock
    RestaurantService restaurantService;

    @InjectMocks
    RestaurantController restaurantController;

    @BeforeEach
    public void init() throws BookingException {

        MockitoAnnotations.openMocks(this);

        RESTAURANT_REST.setName(NAME);
        RESTAURANT_REST.setAddress(ADDRESS);
        RESTAURANT_REST.setDescription(DESCRIPTION);
        RESTAURANT_REST.setId(RESTAURANT_ID);
        RESTAURANT_REST.setImage(IMAGE);
        RESTAURANT_REST.setTurns(TURN_LIST);

        Mockito.when(restaurantService.getRestaurantById(RESTAURANT_ID)).thenReturn(RESTAURANT_REST);

    }

    @Test
    void getRestaurantByIdTest() throws BookingException {

        final BookingResponseRest<RestaurantRest> response = restaurantController.getRestaurantById(RESTAURANT_ID);

        System.out.println(response.toString());

        assertEquals(SUCCESS_STATUS, response.getStatus());
        assertEquals(SUCCESS_CODE, response.getCode());
        assertEquals(SUCCESS_MESSAGE, response.getMessage());
        assertEquals(RESTAURANT_REST, response.getData());

    }

    @Test
    public void getRestaurantsTest() throws BookingException{

        final BookingResponseRest<List<RestaurantRest>> response = restaurantController.getRestaurants();

        assertEquals(SUCCESS_STATUS, response.getStatus());
        assertEquals(SUCCESS_CODE, response.getCode());
        assertEquals(SUCCESS_MESSAGE, response.getMessage());
        assertEquals(RESTAURANT_REST_LIST, response.getData());
    }

}