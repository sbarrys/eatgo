package kr.co.eatgo.interfaces;

import kr.co.eatgo.application.ReviewService;
import kr.co.eatgo.domain.Review;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
public class ReviewController {

    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService){
        this.reviewService= reviewService;
    }

    @GetMapping("/reviews")
    public List<Review> getAllreviews(){
        return reviewService.getAllReviews();
    }
    @PostMapping("/restaurants/{restaurantId}/reviews")
    public ResponseEntity<?> allReviews(
            @PathVariable("restaurantId")Long restaurantId ,
            @Valid  @RequestBody Review requestReview
    ) throws URISyntaxException {
        Review review = reviewService.createReview(restaurantId,requestReview);
        return ResponseEntity.created(
                new URI("/restaurants/"+restaurantId+"/reviews/"
                        +review)).body("{}"); //JSON 이니까 응답을 이렇게 준다.
    }
}
