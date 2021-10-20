package co.com.daleb.booking.infraestructure.sql.jpa;

import co.com.daleb.booking.infraestructure.sql.entities.TurnEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TurnRepository extends CrudRepository<TurnEntity,Long> {
}
