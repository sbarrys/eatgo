package kr.co.eatgo.domain;

public class RestaurantNotFoundException extends RuntimeException {

    public RestaurantNotFoundException(Long id){
         super(id+" is NOT FOUND");
    }

}
