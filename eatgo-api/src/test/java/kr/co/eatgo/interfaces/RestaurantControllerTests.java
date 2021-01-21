package kr.co.eatgo.interfaces;

import kr.co.eatgo.application.RestaurantService;
import kr.co.eatgo.domain.MenuItem;
import kr.co.eatgo.domain.Restaurant;
import kr.co.eatgo.domain.RestaurantNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.StringContains.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class) //get 을 누구한테 요청할것인가.  Spring Runner에게 요청할 것이다.
@WebMvcTest(RestaurantController.class)
        // 컨트롤러 테스트한다는 것
class RestaurantControllerTests {
    @Autowired
    private MockMvc mvc;
    //@spyBean 으로 진짜 Service객체를 넣어주고 ,  Service를 이루고 있는 RestaurantRepository나 menuItemRepository 들을 또
    //SpyBean으로 생성해줘야했었다. 하지만, 이제는 가짜 객체인 @MockBean을 사용할 것이다.
    @MockBean//가짜이기 때문에 각 @Test 에서 given().willReturn() 으로 원하는 커스텀리턴값을 만들어줘야한다.
    private RestaurantService restaurantService;

    @Test
    public void list() throws Exception {
        List<Restaurant> restaurants = new ArrayList<>();
        Restaurant restaurant= Restaurant.builder()
                .name("Bob Zip")
                .address("Seoul")
                .id(1004L).build();
        restaurants.add(restaurant);
        given(restaurantService.getAllRestaurants()).willReturn(restaurants);
        mvc.perform(get("/restaurants"))
                .andExpect(status().isOk())
                .andExpect(content().string(
                        containsString("\"name\":\"Bob Zip\"")
                ))
                .andExpect(content().string(
                        containsString("\"id\":1004")
                ));
    }

    @Test
    public void detail() throws Exception {
        Restaurant restaurant= Restaurant.builder()
                .name("ABC Zip")
                .address("Seoul")
                .id(2004L).build();
        restaurant.setMenuItems(Arrays.asList(MenuItem.builder().name("Kimchi").build()));

        Restaurant restaurant2 = Restaurant.builder()
                .id(1004L)
                .name("New Zip")
                .address("Busan").build();

        given(restaurantService.getRestaurantById(2004L)).willReturn(restaurant);
        given(restaurantService.getRestaurantById(1004L)).willReturn(restaurant2);

        mvc.perform(get("/restaurants/2004"))
                .andExpect(status().isOk())
                .andExpect(content().string(
                        containsString("\"id\":2004")
                ))
                .andExpect(content().string(
                        containsString("\"name\":\"ABC Zip\"")
                ))
                .andExpect(content().string(
                        containsString("Kimchi")
                ));
    }
    @Test
    public void detailWithNotFound() throws Exception {
        given(restaurantService.getRestaurantById(404L))
                .willThrow(new RestaurantNotFoundException(404L));

        mvc.perform(get("/restaurants/404"))
                .andExpect(status().isNotFound())
                .andExpect(content().string("{}"));
    }

    @Test
    public void createWithInvalidData() throws Exception {
        mvc.perform(post("/restaurants")
                //정보넘겨주기 ( @RequestBody 로 받는다)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"\",\"address\":\"\"}"))

                .andExpect(status().isBadRequest());

    }
    @Test
    public void create() throws Exception {
        given(restaurantService.addRestaurant(any())).will(invocation -> {
            Restaurant restaurant= invocation.getArgument(0);
            restaurant.setId(1004L);
            return   restaurant;
        });
        mvc.perform(post("/restaurants")
                //정보넘겨주기 ( @RequestBody 로 받는다)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"God Zip\",\"address\":\"Busan\"}"))

                .andExpect(status().isCreated())
                .andExpect(header().string("location", "/restaurants/1004"))
                .andExpect(content().string("{}"));

        verify(restaurantService).addRestaurant(any());//생성된 mock은 자신의 모든 행동을 기억하는데, verify()를 이용해서 원하는 메소드가 특정 조건으로 실행되었는지를 검증할 수 있다.

    }

    @Test
    public void updateWithInvalidData() throws Exception {
        mvc.perform(patch("/restaurants/1004") //@PatchMapping("/{id}")   +  매개변수 @PathVariable 로 받는다.
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"\",\"address\":\"\"}"))
                .andExpect(status().isBadRequest());

    }
    @Test
    public void update() throws Exception {
        mvc.perform(patch("/restaurants/1004") //@PatchMapping("/{id}")   +  매개변수 @PathVariable 로 받는다.
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"Bob Zip2\",\"address\":\"Cheongju\"}"))
                .andExpect(status().isOk());

        verify(restaurantService).updateRestaurant(1004L,"Bob Zip2","Cheongju");
////        .andExpect(content().string("{\"name\":\"Bob Zip2\",\"address\":\"Cheongju"));

    }

}
