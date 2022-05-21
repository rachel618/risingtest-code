package com.example.demo.src.review;

import com.example.demo.config.BaseException;
import com.example.demo.src.review.model.GetProductReviewRes;
import com.example.demo.src.review.model.GetUserReview;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.demo.config.BaseResponseStatus.DATABASE_ERROR;

@Service
public class ReviewProvider {

    final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private final ReviewDao reviewDao;

    public ReviewProvider(ReviewDao reviewDao) {
        this.reviewDao = reviewDao;
    }

    public List<GetProductReviewRes> getReviews(int productIdx) throws BaseException {
        try {
            List<GetProductReviewRes> getReviewsRes = reviewDao.getReviews(productIdx);
            return getReviewsRes;
        }catch(Exception exception){
            throw new BaseException(DATABASE_ERROR);
        }
    }
    public List<GetUserReview> getUserReviews(int storeIdx) throws BaseException {
        try {
            List<GetUserReview> getReviewsRes = reviewDao.getUserReviews(storeIdx);
            return getReviewsRes;
        }catch(Exception exception){
            throw new BaseException(DATABASE_ERROR);
        }
    }
    public List<GetProductReviewRes> getReviewsOrderByRating(int storeIdx) throws BaseException {
        try {
            List<GetProductReviewRes> getReviewsRes = reviewDao.getReviewsOrderByRating(storeIdx);
            return getReviewsRes;
        }catch(Exception exception){
            throw new BaseException(DATABASE_ERROR);
        }
    }

}
