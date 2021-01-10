package kr.co.eatgo.domain;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class RestaurantRepositoryImpl implements RestaurantRepository {

    private List<Restaurant> restaurants = new ArrayList<>();

    public RestaurantRepositoryImpl(){
        Restaurant restaurant1 = new Restaurant(1004L,"Bob Zip","Seoul");
        Restaurant restaurant2 = new Restaurant(2004L,"Cyber Food","Seoul");
        restaurants.add(restaurant1);
        restaurants.add(restaurant2);

    }
    @Override
    public List<Restaurant> findAll(){
        return restaurants;
    }

    @Override
    public Restaurant findById(Long id){
        //리스트로 부터 특정 객체를 가져오는 방법
        Restaurant restaurant = restaurants.stream()
                .filter(r-> r.getId().equals(id))
                .findFirst()
                .orElse(null);

        return restaurant;
    }

    @Override
    public Restaurant save(Restaurant restaurant) {
        restaurant.setId(3004L);
        restaurants.add(restaurant);
        return restaurant;
    }
}
