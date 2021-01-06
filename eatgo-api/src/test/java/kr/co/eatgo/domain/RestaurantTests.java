package kr.co.eatgo.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import  static org.hamcrest.MatcherAssert.assertThat;
import  static org.hamcrest.CoreMatchers.is;

public class RestaurantTests {
    @Test
    @DisplayName("식당이름은 BobZip")
    public void creation(){
        Restaurant restaurant = new Restaurant();
        assertThat(restaurant.getName(),is("Bob Zip"));
    }

}
