package co.com.daleb.booking.domain.services;

import co.com.daleb.booking.domain.exceptions.BookingException;
import co.com.daleb.booking.domain.jsons.RestaurantRest;
import java.util.List;

public interface RestaurantService {
  RestaurantRest getRestaurantById(Long restaurantId) throws BookingException;

  List<RestaurantRest> getRestaurants() throws BookingException;
}
