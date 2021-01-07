package kr.co.eatgo.application;

import com.sun.org.apache.regexp.internal.RE;
import kr.co.eatgo.domain.MenuItem;
import kr.co.eatgo.domain.MenuItemRepository;
import kr.co.eatgo.domain.Restaurant;
import kr.co.eatgo.domain.RestaurantRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
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
        Restaurant restaurant = restaurantRepository.findById(id);
        List<MenuItem> menuItems = menuItemRepository.findAllByRestaurantId(id);
        restaurant.setMenuItems(menuItems);
        return restaurant;
    }
}
