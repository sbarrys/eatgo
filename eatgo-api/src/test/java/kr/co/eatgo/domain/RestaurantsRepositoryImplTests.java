package kr.co.eatgo.domain;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class RestaurantsRepositoryImplTests {
    @Test
    public void save(){
        RestaurantRepository repository = new RestaurantRepositoryImpl();

        int oldCount= repository.findAll().size();

        Restaurant restaurant= new Restaurant("New Bob Zip","Busan");

        repository.save(restaurant);

        int newCount = repository.findAll().size();

        assertThat(newCount-oldCount,is(1));
    }
}
