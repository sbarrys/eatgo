package kr.co.eatgo.interfaces;

import kr.co.eatgo.application.RestaurantService;
import kr.co.eatgo.domain.*;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import java.util.ArrayList;
import java.util.List;
import static org.hamcrest.core.StringContains.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class) //get 을 누구한테 요청할것인가.  Spring Runner에게 요청할 것이다.
@WebMvcTest(RestaurantController.class) // 컨트롤러 테스트한다는 것
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
        restaurants.add(new Restaurant(1004L,"Bob Zip","Seoul"));

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
        Restaurant restaurant=new Restaurant(2004L,"ABC Zip","Seoul");
        restaurant.addMenuItem(new MenuItem("Kimchi"));
        given(restaurantService.getRestaurantById(2004L)).willReturn(restaurant);

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
    public void create() throws Exception {
        mvc.perform(post("/restaurants")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"God Zip\",\"address\":\"Busan\"}"))

                .andExpect(status().isCreated())
                .andExpect(header().string("location","/restaurants/3004"))
                .andExpect(content().string("{}"));

        verify(restaurantService).addRestaurant(any());//생성된 mock은 자신의 모든 행동을 기억하는데, verify()를 이용해서 원하는 메소드가 특정 조건으로 실행되었는지를 검증할 수 있다.

    }
}
