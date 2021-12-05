package co.com.daleb.booking.infraestructure.sql.jpa;

import co.com.daleb.booking.infraestructure.sql.entities.RestaurantEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RestaurantRepository extends CrudRepository<RestaurantEntity,Long> {

    List<RestaurantEntity> findAll();

    Optional<RestaurantEntity> findByName(String name);

}
