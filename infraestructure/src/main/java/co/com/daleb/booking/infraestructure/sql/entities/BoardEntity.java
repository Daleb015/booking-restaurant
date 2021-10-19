package co.com.daleb.booking.infraestructure.sql.entities;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "BOARD")
public class BoardEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID",unique = true, nullable = false)
    private Long id;

    @Column(name = "CAPACITY")
    private Long capacity;

    @Column(name = "NUMBER")
    private Long number;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "RESTAURANT_iD",nullable = false)
    private RestaurantEntity restaurant;

}
