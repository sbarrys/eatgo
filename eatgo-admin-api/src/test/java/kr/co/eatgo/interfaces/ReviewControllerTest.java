package kr.co.eatgo.interfaces;


import kr.co.eatgo.application.ReviewService;
import kr.co.eatgo.domain.Review;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@WebMvcTest(ReviewController.class)
@RunWith(SpringRunner.class)//Mockbean 사용할 때, 객체를 가져다준다.
public class ReviewControllerTest {
    @Autowired
    MockMvc mockMvc;
    @MockBean
    ReviewService reviewService;


    @Test
    public void list() throws Exception {
        List<Review> reviews = new ArrayList<>();
        reviews.add(Review.builder().score(5).description("Cool!").build());
        given(reviewService.getAllReviews()).willReturn(reviews);
        mockMvc.perform(get("/reviews"))
                .andExpect(content().string(containsString("Cool!")));
    }

}
