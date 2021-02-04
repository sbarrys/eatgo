package kr.co.eatgo.application;


import kr.co.eatgo.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class RestaurantService {
    private RestaurantRepository restaurantRepository;
    public RestaurantService( RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    public List<Restaurant> getAllRestaurants(){
        List<Restaurant> restaurants = restaurantRepository.findAll();

        return restaurants;
    }

    public Restaurant getRestaurantById(Long id){
        Restaurant restaurant = restaurantRepository.findById(id).orElseThrow(()->new RestaurantNotFoundException(id));
        return restaurant;
    }

    public Restaurant addRestaurant(Restaurant restaurant) {
        return restaurantRepository.save(restaurant);
    }
    public Restaurant updateRestaurant(Long id , String name, String address){
        Restaurant restaurant = restaurantRepository.findById(id).orElse(null);
        restaurant.updateInformation(name,address);
        return restaurant;
    }
}
