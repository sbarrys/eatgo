package kr.co.eatgo.interfaces;

import kr.co.eatgo.application.RestaurantService;
import kr.co.eatgo.domain.MenuItemRepository;
import kr.co.eatgo.domain.MenuItemRepositoryImpl;
import kr.co.eatgo.domain.RestaurantRepositoryImpl;
import org.hamcrest.core.StringContains;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
        mvc.perform(MockMvcRequestBuilders.get("/restaurants"))
                .andExpect(status().isOk())
                .andExpect(content().string(
                        StringContains.containsString("\"name\":\"Bob Zip\"")
                ))
                .andExpect(content().string(
                        StringContains.containsString("\"id\":1004")
                ));
    }

    @Test
    public void detail() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/restaurants"))
                .andExpect(status().isOk())
                .andExpect(content().string(
                        StringContains.containsString("\"name\":\"Bob Zip\"")
                ))
                .andExpect(content().string(
                        StringContains.containsString("\"id\":1004")
                ));

        mvc.perform(MockMvcRequestBuilders.get("/restaurants/2004"))
                .andExpect(status().isOk())
                .andExpect(content().string(
                        StringContains.containsString("\"id\":2004")
                ))
                .andExpect(content().string(
                        StringContains.containsString("\"name\":\"Cyber Food\"")
                ))
                .andExpect(content().string(
                        StringContains.containsString("Kimchi")
                ));


    }
}
