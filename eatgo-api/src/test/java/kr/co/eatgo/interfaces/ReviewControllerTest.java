package kr.co.eatgo.interfaces;


import kr.co.eatgo.application.ReviewService;
import kr.co.eatgo.domain.Review;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ReviewController.class)
@RunWith(SpringRunner.class)//Mockbean 사용할 때, 객체를 가져다준다.
public class ReviewControllerTest {
    @Autowired
    MockMvc mockMvc;
    @MockBean
    ReviewService reviewService;
    @Test
    public void create() throws Exception {
        Review review = Review.builder().name("Bob Zip").description("GoodGood Very Good").score(5).build();
        given(reviewService.createReview(review)).willReturn(
                Review.builder().id(1L).name("Bob Zip").description("GoodGood Very Good").score(5).build());

        mockMvc.perform(MockMvcRequestBuilders.post("/restaurants/1/reviews")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"id\":1,\"name\":\"Bob Zip\", \"score\":5, \"description\":\"GoodGood Very Good\"}")
                ).andExpect(status().isCreated())
                .andExpect(content().string("/restaurants/1/reviews/1004"));

        verify(reviewService,times(1)).createReview(any());
    }

}
