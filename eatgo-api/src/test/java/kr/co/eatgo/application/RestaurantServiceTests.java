package kr.co.eatgo.application;

import kr.co.eatgo.domain.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.assertThrows;
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

        Restaurant restaurant= Restaurant.builder()
                .name("Bob Zip")
                .address("Seoul")
                .id(1004L).build();

        restaurants.add(restaurant);
        List<MenuItem> menuItems = new ArrayList<>();
        menuItems.add(MenuItem.builder().name("Kimchi").build());
        given(menuItemRepository.findAllByRestaurantId(1004L)).willReturn(menuItems);
        given(restaurantRepository.findAll()).willReturn(restaurants);
        given(restaurantRepository.findById(1004L)).willReturn(java.util.Optional.of(restaurant));


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
    public void getRestaurantWithNotFound(){

        Throwable exception = assertThrows(RestaurantNotFoundException.class, () -> {
            restaurantService.getRestaurantById(404L);
        });

    }

    @Test
    public void getRestaurantAll(){
        List<Restaurant> restaurants = restaurantService.getAllRestaurants();

        Restaurant restaurant = restaurants.get(0);
        assertThat(restaurant.getId(),is(1004L));
    }

    @Test
    public void addRestaurant(){

        Restaurant restaurant = Restaurant.builder().name("New Bob Zip").address("Busan").build();
        Restaurant saved = Restaurant.builder().id(3004L).name("New Bob Zip").address("Busan").build();
        given(restaurantRepository.save(any())).willReturn(saved);

        Restaurant createdRestaurant = restaurantService.addRestaurant(restaurant);

        assertThat(createdRestaurant.getId(),is(3004L));
    }
    @Test
    public void updateRestaurant(){

        Restaurant restaurant= Restaurant.builder()
                .name("ABC Zip")
                .address("Seoul")
                .id(1004L).build();

        given(restaurantRepository.findById(1004L)).willReturn(Optional.of(restaurant));

        restaurantService.updateRestaurant(1004L,"New Bob Zip", "Seoul");

        assertThat(restaurant.getName(),is("New Bob Zip"));
        assertThat(restaurant.getAddress(),is("Seoul"));
    }
}
