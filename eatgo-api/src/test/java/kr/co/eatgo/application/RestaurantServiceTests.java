package kr.co.eatgo.application;

import kr.co.eatgo.domain.*;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.SpyBean;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class RestaurantServiceTests {
    private RestaurantService restaurantService;
    private RestaurantRepository restaurantRepository;
    private MenuItemRepository  menuItemRepository;
    @Before
    public void setUp(){
        restaurantRepository = new RestaurantRepositoryImpl();
        menuItemRepository =new MenuItemRepositoryImpl();
        restaurantService = new RestaurantService(menuItemRepository,restaurantRepository);
    }
    @Test
    public void getRestaurant(){
        Restaurant restaurant= restaurantService.getRestaurantById(1004L);
        assertThat(restaurant.getId(),is(10004L));

    }

    @Test
    public void getRestaurantAll(){
        List<Restaurant> restaurants = restaurantService.getAllRestaurants();

        Restaurant restaurant = restaurants.get(0);
        assertThat(restaurant.getId(),is(1004L));
    }
}
