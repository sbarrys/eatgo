package kr.co.eatgo.application;


import kr.co.eatgo.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class RestaurantService {
    private MenuItemRepository menuItemRepository;
    private RestaurantRepository restaurantRepository;
    private ReviewRepository reviewRepository;
    public RestaurantService(MenuItemRepository menuItemRepository, RestaurantRepository restaurantRepository,ReviewRepository reviewRepository) {
        this.menuItemRepository = menuItemRepository;
        this.restaurantRepository = restaurantRepository;
        this.reviewRepository= reviewRepository;
    }

    public List<Restaurant> getAllRestaurants(){
        List<Restaurant> restaurants = restaurantRepository.findAll();

        return restaurants;
    }

    public Restaurant getRestaurantById(Long id){
        Restaurant restaurant = restaurantRepository.findById(id).orElseThrow(()->new RestaurantNotFoundException(id));
        List<MenuItem> menuItems = menuItemRepository.findAllByRestaurantId(id);
        List<Review> reviews = reviewRepository.findAllByRestaurantId(id);
        restaurant.setMenuItems(menuItems);
        restaurant.setReviews(reviews);
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
