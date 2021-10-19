package co.com.daleb.booking.infraestructure.sql.jpa;

import co.com.daleb.booking.infraestructure.sql.entities.RestaurantEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RestaurantRepository extends CrudRepository<RestaurantEntity,Long> {
    List<RestaurantEntity> findAll();
}
