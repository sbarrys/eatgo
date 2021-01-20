package kr.co.eatgo.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.ArrayList;

import  static org.hamcrest.MatcherAssert.assertThat;
import  static org.hamcrest.CoreMatchers.is;

public class RestaurantTests {
    @Test
    @DisplayName("식당이름은 BobZip")
    public void creation(){
        Restaurant restaurant= Restaurant.builder()
                .name("Bob Zip")
                .address("Seoul")
                .id(1004L).build();

        assertThat(restaurant.getName(),is("Bob Zip"));
    }
    @Test
    @DisplayName("식당 정보 받기")
    public void information(){
        Restaurant restaurant= Restaurant.builder()
                .name("Bob Zip")
                .address("Seoul")
                .id(1004L).build();
        restaurant.setMenuItems(new ArrayList<MenuItem>());
        assertThat(restaurant.getInformation(), is("Bob Zip in Seoul[]"));
    }

}
