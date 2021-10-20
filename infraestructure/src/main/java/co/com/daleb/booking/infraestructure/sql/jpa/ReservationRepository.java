package co.com.daleb.booking.infraestructure.sql.jpa;

import co.com.daleb.booking.infraestructure.sql.entities.ReservationEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
public interface ReservationRepository extends CrudRepository<ReservationEntity,Long> {

    Optional<ReservationEntity> findByLocator(String locator);

    @Transactional
    Optional<ReservationEntity> deleteByLocator(String locator);

    Optional<ReservationEntity> findByTurnAndRestaurantId(String turn,Long restaurantId);

}
