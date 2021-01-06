package kr.co.eatgo.interfaces;

import ch.qos.logback.core.pattern.util.RegularEscapeUtil;
import kr.co.eatgo.domain.Restaurant;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.awt.geom.RectangularShape;
import java.util.ArrayList;
import java.util.List;

@RestController
public class RestaurantController {

    @GetMapping("restaurants")
    public List<Restaurant> list(){
        List<Restaurant> restaurants = new ArrayList<>();
        Restaurant restaurant = new Restaurant(1004L,"Bob Zip","Seoul");
        restaurants.add(restaurant);
        return restaurants;
    }

    @GetMapping("restaurants/{id}")
    public Restaurant detail(@PathVariable("id") Long id){
        List<Restaurant> restaurants = new ArrayList<>();
        Restaurant restaurant1 = new Restaurant(1004L,"Bob Zip","Seoul");
        Restaurant restaurant2 = new Restaurant(2004L,"Cyber Food","Seoul");
        restaurants.add(restaurant1);
        restaurants.add(restaurant2);

        //리스트로 부터 특정 객체를 가져오는 방법
        Restaurant restaurant = restaurants.stream()
                .filter(r-> r.getId().equals(id))
                .findFirst()
                .orElse(null);
        return restaurant;
    }
}
