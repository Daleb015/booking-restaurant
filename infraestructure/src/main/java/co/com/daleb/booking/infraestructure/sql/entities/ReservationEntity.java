package co.com.daleb.booking.infraestructure.sql.entities;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "RESERVATION")
public class ReservationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID",unique = true,nullable = false)
    private Long id;

    @Column(name = "LOCATOR")
    private String locator;

    @Column(name = "TURN")
    private String turn;

    @Column(name = "PERSON")
    private Long person;

    @Column(name = "DATE")
    private LocalDate date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "RESTAURANT_ID",nullable = false)
    private RestaurantEntity restaurant;

}
