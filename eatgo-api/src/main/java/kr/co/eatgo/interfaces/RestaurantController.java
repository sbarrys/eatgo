package kr.co.eatgo.interfaces;

import kr.co.eatgo.application.RestaurantService;
import kr.co.eatgo.domain.MenuItem;
import kr.co.eatgo.domain.MenuItemRepository;
import kr.co.eatgo.domain.Restaurant;
import kr.co.eatgo.domain.RestaurantRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.ws.Response;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@RequestMapping("restaurants")
@RestController
public class RestaurantController {
    //구현체를 직접 주입하는 것이 아닌 인터페이스를 주입해준다. 이것이 DI 로 부터 얻을 수 있는 장점이다.
    private RestaurantService restaurantService;
    public RestaurantController(RestaurantService restaurantService){
        this.restaurantService= restaurantService;
    }
    @GetMapping
    public List<Restaurant> list(){
        List<Restaurant> restaurants =
                restaurantService.getAllRestaurants();
        return restaurants;
    }

    @GetMapping("/{id}")
    public Restaurant detail(@PathVariable("id") Long id){
        Restaurant restaurant = restaurantService.getRestaurantById(id);
        return restaurant;
    }
    @PostMapping
    public ResponseEntity<?> create(@RequestBody Restaurant body) throws URISyntaxException {
        String name= body.getName();
        String address = body.getAddress();

        Restaurant restaurant = restaurantService.addRestaurant(new Restaurant(name,address));
        URI location = new URI("/restaurants/"+restaurant.getId());
        return ResponseEntity.created(location).body("{}");
    }
    @PatchMapping("/{id}")
    public String update(@PathVariable("id") Long id,@RequestBody Restaurant body) throws URISyntaxException {
        String name = body.getName();
        String address= body.getAddress();

        Restaurant restaurant= restaurantService.updateRestaurant(id,name,address);
//        URI location = new URI("/restaurants/"+restaurant.getId());
        return "{}";
    }
}
