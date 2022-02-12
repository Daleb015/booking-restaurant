package co.com.daleb.booking.infraestructure.sql.jpa;

import co.com.daleb.booking.infraestructure.sql.entities.ReservationEntity;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationRepository extends CrudRepository<ReservationEntity, Long> {
  Optional<ReservationEntity> findByLocator(String locator);

  @Transactional
  Optional<ReservationEntity> deleteByLocator(String locator);

  Optional<ReservationEntity> findByTurnAndRestaurantId(String turn, Long restaurantId);
}
