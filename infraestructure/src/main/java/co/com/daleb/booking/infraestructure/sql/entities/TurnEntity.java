package co.com.daleb.booking.infraestructure.sql.entities;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "TURN")
public class TurnEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID",unique = true, nullable = false)
    private Long id;

    @Column(name = "NAME")
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "RESTAURANT_iD",nullable = false)
    private RestaurantEntity restaurant;

}
