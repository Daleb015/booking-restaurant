package co.com.daleb.booking.domain.services.impl;

import co.com.daleb.booking.domain.exceptions.BookingException;
import co.com.daleb.booking.domain.exceptions.NotFoundException;
import co.com.daleb.booking.domain.jsons.RestaurantRest;
import co.com.daleb.booking.domain.services.RestaurantService;
import co.com.daleb.booking.infraestructure.sql.entities.RestaurantEntity;
import co.com.daleb.booking.infraestructure.sql.jpa.RestaurantRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Data
@RequiredArgsConstructor
@Service
public class RestaurantServiceImpl implements RestaurantService {

  private final RestaurantRepository restaurantRepository;

  private final ModelMapper modelMapper;

  @Override
  public RestaurantRest getRestaurantById(Long restaurantId) throws BookingException {
    return modelMapper.map(getRestaurantEntity(restaurantId), RestaurantRest.class);
  }

  @Override
  public List<RestaurantRest> getRestaurants() throws BookingException {
    final List<RestaurantEntity> restaurantEntities = restaurantRepository.findAll();
    return restaurantEntities
      .stream()
      .map(service -> modelMapper.map(service, RestaurantRest.class))
      .collect(Collectors.toList());
  }

  private RestaurantEntity getRestaurantEntity(Long restaurantId) throws BookingException {
    return restaurantRepository
      .findById(restaurantId)
      .orElseThrow(() -> new NotFoundException("SNOT-404-1", "RESTAURANT_NOT_FOUND"));
  }
}
