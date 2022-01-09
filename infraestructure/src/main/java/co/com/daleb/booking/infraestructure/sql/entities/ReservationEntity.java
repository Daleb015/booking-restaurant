package co.com.daleb.booking.infraestructure.sql.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;


@AllArgsConstructor
@NoArgsConstructor
@Builder
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

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "NAME")
    private String name;

    @Column(name = "PAYMENT")
    private Boolean payment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "RESTAURANT_ID",nullable = false)
    private RestaurantEntity restaurant;

}
