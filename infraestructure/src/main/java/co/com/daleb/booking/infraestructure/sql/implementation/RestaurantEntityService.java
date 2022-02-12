package co.com.daleb.booking.infraestructure.sql.implementation;

import co.com.daleb.booking.infraestructure.sql.entities.RestaurantEntity;
import co.com.daleb.booking.infraestructure.sql.jpa.RestaurantRepository;
import java.util.List;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Transactional
@RequiredArgsConstructor
@Service
public class RestaurantEntityService {

  private RestaurantRepository restaurantRepository;

  public List<RestaurantEntity> findAll() {
    return restaurantRepository.findAll();
  }
}
