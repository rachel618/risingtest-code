package com.example.demo.src.review;

import com.example.demo.config.BaseException;
import com.example.demo.src.review.model.PostReviewReq;
import com.example.demo.src.review.model.PostReviewRes;
import com.example.demo.src.review.model.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.example.demo.config.BaseResponseStatus.DATABASE_ERROR;

@Service
public class ReviewService {
    @Autowired
    private final ReviewProvider reviewProvider;
    @Autowired
    private final ReviewDao reviewDao;

    public ReviewService(ReviewProvider reviewProvider, ReviewDao reviewDao) {
        this.reviewProvider = reviewProvider;
        this.reviewDao = reviewDao;
    }

    public PostReviewRes writeReview(int productIdx, int userIdx , PostReviewReq postReviewReq) throws BaseException {
        try{
            return reviewDao.writeReview(productIdx, userIdx ,postReviewReq);
        }catch(Exception exception){
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public void deleteReview(int reviewIdx) throws BaseException {
        try{
            reviewDao.deleteReview(reviewIdx);
        }catch(Exception exception){
            throw new BaseException(DATABASE_ERROR);
        }
    }
}
