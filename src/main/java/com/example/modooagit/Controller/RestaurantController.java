package com.example.modooagit.Controller;

import com.example.modooagit.domain.Restaurant;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class RestaurantController{

    @GetMapping("/restoraurant")
    public List<Restaurant> list(){
        List<Restaurant> restaurants = new ArrayList<>();

        Restaurant restaurant = new Restaurant("bob zip", "swoul");
        restaurants.add(restaurant);
        return null;
    }
}
