package kr.co.eatgo.application;

import kr.co.eatgo.domain.Review;
import kr.co.eatgo.domain.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ReviewService {

    private ReviewRepository reviewRepository;
    @Autowired
    public ReviewService( ReviewRepository reviewRepository){
        this.reviewRepository = reviewRepository;
    }


    public Review createReview(Review review) {

        Review returnreview = reviewRepository.save(review);
        return returnreview;
    }
}
