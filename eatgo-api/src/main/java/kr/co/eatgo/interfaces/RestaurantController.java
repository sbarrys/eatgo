package kr.co.eatgo.interfaces;

import kr.co.eatgo.domain.Restaurant;
import kr.co.eatgo.domain.RestaurantRepository;
import kr.co.eatgo.domain.RestaurantRepositoryImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RestaurantController {
    //구현체를 직접 주입하는 것이 아닌 인터페이스를 주입해준다. 이것이 DI 로 부터 얻을 수 있는 장점이다.
    private RestaurantRepository restaurantRepository;

    public RestaurantController(RestaurantRepository restaurantRepository){
        this.restaurantRepository = restaurantRepository;
    }
    @GetMapping("restaurants")
    public List<Restaurant> list(){
        List<Restaurant> restaurants = restaurantRepository.findAll();
        return restaurants;
    }

    @GetMapping("restaurants/{id}")
    public Restaurant detail(@PathVariable("id") Long id){
        Restaurant restaurant = restaurantRepository.findById(id);
        return restaurant;
    }
}
