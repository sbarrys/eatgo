package kr.co.eatgo.application;

import kr.co.eatgo.domain.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

class RestaurantServiceTests {

    private RestaurantService restaurantService;

    @Mock
    private RestaurantRepository restaurantRepository;

    @Mock
    private MenuItemRepository  menuItemRepository;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.initMocks(this); // @Mock 어노테이션이 있는 것들을 초기화 해줌

        List<Restaurant> restaurants = new ArrayList<>();
        Restaurant restaurant = new Restaurant(1004L,"Bob Zip" , "Seoul");
        restaurants.add(restaurant);
        List<MenuItem> menuItems = new ArrayList<>();
        menuItems.add(new MenuItem("Kimchi"));
        given(menuItemRepository.findAllByRestaurantId(1004L)).willReturn(menuItems);
        given(restaurantRepository.findAll()).willReturn(restaurants);
        given(restaurantRepository.findById(1004L)).willReturn(restaurant);


        //@Mock으로 했기때문에 구현하지 않아도됨
//        restaurantRepository = new RestaurantRepositoryImpl();
//        menuItemRepository =new MenuItemRepositoryImpl();
        restaurantService = new RestaurantService(menuItemRepository,restaurantRepository);
    }

    @Test
    public void getRestaurant(){
        Restaurant restaurant= restaurantService.getRestaurantById(1004L);
        assertThat(restaurant.getId(),is(1004L));

    }

    @Test
    public void getRestaurantAll(){
        List<Restaurant> restaurants = restaurantService.getAllRestaurants();

        Restaurant restaurant = restaurants.get(0);
        assertThat(restaurant.getId(),is(1004L));
    }

    @Test
    public void addRestaurant(){
        Restaurant restaurant = new Restaurant("New Bob Zip" ,"Busan");
        Restaurant saved = new Restaurant(3004L,"New Bob Zip" ,"Busan");
        given(restaurantRepository.save(any())).willReturn(saved);

        Restaurant createdRestaurant = restaurantService.addRestaurant(restaurant);

        assertThat(createdRestaurant.getId(),is(3004L));
    }
}
