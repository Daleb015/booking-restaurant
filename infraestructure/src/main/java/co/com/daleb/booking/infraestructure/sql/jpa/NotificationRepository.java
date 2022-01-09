package co.com.daleb.booking.infraestructure.sql.jpa;

import co.com.daleb.booking.infraestructure.sql.entities.NotificationEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NotificationRepository extends CrudRepository<NotificationEntity,Integer> {

    Optional<NotificationEntity> findByTemplateType(String templateType);

}
