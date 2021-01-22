package kr.co.eatgo.interfaces;

import kr.co.eatgo.application.ReviewService;
import kr.co.eatgo.domain.Review;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

@RestController
public class ReviewController {

    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService){
        this.reviewService= reviewService;
    }

    @PostMapping("/restaurants/{restaurantId}/reviews")
    public ResponseEntity<?> allReviews(
            @PathVariable("restaurantId")Long restaurantId ,
            @Valid  @RequestBody Review requestReview
    ) throws URISyntaxException {
        Review review = reviewService.createReview(requestReview);
        return ResponseEntity.created(
                new URI("/restaurants/"+restaurantId+"/reviews/"
                        +review.getId())).body("{}"); //JSON 이니까 응답을 이렇게 준다.
    }
}
