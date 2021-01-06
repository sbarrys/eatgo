package kr.co.eatgo.interfaces;

import org.hamcrest.core.StringContains;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
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

    @Test
    public void list() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/restaurants"))
                .andExpect(status().isOk())
                .andExpect(content().string(
                        StringContains.containsString("\"name\":\"Bob Zip\"")
                ))
                .andExpect(content().string(
                        StringContains.containsString("\"id\":1004")
                ));
    }
}
