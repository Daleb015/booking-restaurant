package co.com.daleb.booking.infraestructure.sql.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@Table(name = "NOTIFICATION")
public class NotificationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID",unique = true,nullable = false)
    private Long id;

    @Column(name = "TEMPLATE")
    private String template;

    @Column(name = "TEMPLATE_CODE")
    private String templateCode;

}
