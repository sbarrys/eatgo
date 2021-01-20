package kr.co.eatgo.domain;

public class RestaurantaNotFoundException extends RuntimeException {

    public RestaurantaNotFoundException(Long id){
         super(id+" is NOT FOUND");
    }

}
