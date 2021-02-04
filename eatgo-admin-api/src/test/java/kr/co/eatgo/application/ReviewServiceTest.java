package kr.co.eatgo.application;

import kr.co.eatgo.domain.Review;
import kr.co.eatgo.domain.ReviewRepository;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@RunWith(SpringRunner.class)
public class ReviewServiceTest {
    @InjectMocks
    private ReviewService reviewService;
    @Mock
    private ReviewRepository reviewRepository;
    @BeforeEach
    public void setUp(){
        MockitoAnnotations.initMocks(this);
    }
    @Test
    public void getAllReviewsTest(){
        List<Review> mockReviews= new ArrayList<>();
        Review review= Review.builder().score(5).description("Cool!").build();
        mockReviews.add(review);
        given(reviewRepository.findAll()).willReturn(mockReviews);
        List<Review> returnReviews = reviewService.getAllReviews();
        Review testReview = returnReviews.get(0);
        assertThat(testReview.getDescription(),is("Cool!"));
    }
    @Test
    public void createReviewTest(){
        Review review = Review.builder().name("Bob Zip").description("GoodGood Very Good").score(5).build();
        reviewService.createReview(1004L,review);
        verify(reviewRepository).save(any());

    }
}
