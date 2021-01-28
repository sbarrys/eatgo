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

import static org.mockito.ArgumentMatchers.any;
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
    public void createReviewTest(){
        Review review = Review.builder().name("Bob Zip").description("GoodGood Very Good").score(5).build();
        reviewService.createReview(1004L,review);
        verify(reviewRepository).save(any());

    }
}
