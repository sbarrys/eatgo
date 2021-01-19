package kr.co.eatgo.application;


import kr.co.eatgo.domain.MenuItem;
import kr.co.eatgo.domain.MenuItemRepository;
import kr.co.eatgo.domain.Restaurant;
import kr.co.eatgo.domain.RestaurantRepository;
import lombok.extern.log4j.Log4j;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class RestaurantService {
    private MenuItemRepository menuItemRepository;
    private RestaurantRepository restaurantRepository;

    public RestaurantService(MenuItemRepository menuItemRepository, RestaurantRepository restaurantRepository) {
        this.menuItemRepository = menuItemRepository;
        this.restaurantRepository = restaurantRepository;
    }

    public List<Restaurant> getAllRestaurants(){
        List<Restaurant> restaurants = restaurantRepository.findAll();

        return restaurants;
    }

    public Restaurant getRestaurantById(Long id){
        Restaurant restaurant = restaurantRepository.findById(id).orElse(null);
        List<MenuItem> menuItems = menuItemRepository.findAllByRestaurantId(id);
        restaurant.setMenuItems(menuItems);
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
