package co.com.daleb.booking.application.controllers;

import co.com.daleb.booking.infraestructure.sql.entities.RestaurantEntity;
import co.com.daleb.booking.infraestructure.sql.implementation.RestaurantEntityService;
import co.com.daleb.booking.infraestructure.sql.jpa.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(name = "/api")
public class RestaurantController {

    @Autowired
    private RestaurantEntityService restaurantEntityService;

    @GetMapping()
    private List<RestaurantEntity> get(){
        return restaurantEntityService.findAll();
    }

}
