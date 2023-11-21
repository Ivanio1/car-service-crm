package carservicecrm.services;

import carservicecrm.models.Review;
import carservicecrm.repositories.ReviewRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;


    public List<Review> list() {
        return reviewRepository.findAll();
    }

    public boolean saveReview(Review review) {
        try{
            reviewRepository.save(review);
        }catch (Exception e){
            return false;
        }
        return true;
    }
}
