package com.example.demo.src.review;

import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponse;
import com.example.demo.src.review.model.GetProductReviewRes;
import com.example.demo.src.review.model.PostReviewReq;
import com.example.demo.src.review.model.PostReviewRes;
import com.example.demo.src.review.model.Review;
import com.example.demo.utils.JwtService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.demo.config.BaseResponseStatus.*;


@RestController
@RequestMapping("")
public class ReviewController {

    final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private final JwtService jwtService;
    @Autowired
    private final ReviewProvider reviewProvider;
    @Autowired
    private final ReviewService reviewService;

    public ReviewController(ReviewProvider reviewProvider, JwtService jwtService, ReviewService reviewService) {
        this.reviewProvider = reviewProvider;
        this.jwtService=jwtService;
        this.reviewService=reviewService;
    }

//    @ResponseBody
//    @GetMapping("")
//    public BaseResponse<List<GetProductReviewRes>> getReviews(int storeIdx){
//        try{
//            List<GetProductReviewRes> getReviewsRes=ReviewProvider.getReviews(storeIdx);
//            return new BaseResponse<>(getReviewsRes);
//        } catch(BaseException exception){
//                return new BaseResponse<>((exception.getStatus()));
//        }
//    }


    /**
     * 리뷰작성 API
     * @param userIdx
     * @param postReviewReq
     * @return
     */
    @ResponseBody
    @PostMapping("/reviews/{productIdx}/{userIdx}")
    public BaseResponse<PostReviewRes> writeReview(@PathVariable("productIdx") int productIdx, @PathVariable("userIdx") int userIdx, @RequestBody PostReviewReq postReviewReq){
        try{

            int userIdxByJwt = jwtService.getUserIdx();
            //userIdx와 접근한 유저가 같은지 확인
            if (userIdx != userIdxByJwt) {
                return new BaseResponse<>(INVALID_USER_JWT);
            }
            PostReviewRes review= reviewService.writeReview(productIdx, userIdx ,postReviewReq);
            return new BaseResponse<>(SUCCESS_TO_POST_REVIEW,review);
        }catch(BaseException exception){
            return new BaseResponse<>(exception.getStatus());
        }
    }

    @ResponseBody
    @PatchMapping("/reviews/{reviewIdx}/{userIdx}")
    public BaseResponse<String> deleteReview(@PathVariable("reviewIdx") int reviewIdx, @PathVariable("userIdx") int userIdx){
        try{
            int userIdxByJwt = jwtService.getUserIdx();
            //userIdx와 접근한 유저가 같은지 확인
            if (userIdx != userIdxByJwt) {
                return new BaseResponse<>(INVALID_USER_JWT);
            }
            reviewService.deleteReview(reviewIdx);
            return new BaseResponse<>(SUCCESS_TO_DELETE_REVIEW);
        }catch(BaseException exception){
            return new BaseResponse<>(exception.getStatus());
        }
    }




    /**
     * 상품 리뷰조회 API
     * @param productIdx
     * @param order
     * @return
     */
    @ResponseBody
    @GetMapping("/reviews/{productIdx}")
    public BaseResponse<List<GetProductReviewRes>> getReview(@PathVariable("productIdx") int productIdx, @RequestParam(required = false) String order){
        try{
            List<GetProductReviewRes> reviews;
            if(order==null)
                reviews=reviewProvider.getReviews(productIdx);
            else // order=="rating"
                reviews=reviewProvider.getReviewsOrderByRating(productIdx);

            return new BaseResponse<>(SUCCESS_TO_GET_REVIEWS,reviews);
        }catch(BaseException exception){
            return new BaseResponse<>(exception.getStatus());
        }
    }
//
//    @ResponseBody
//    @GetMapping("/reviews/{userIdx}")
//    public BaseResponse<List<GetUserReview>> getUserReviews(@PathVariable("userIdx")int userIdx){
//        try{
//            List<GetUserReview> reviews;
//            reviews=reviewProvider.getUserReviews(userIdx);
//            String result = "리뷰조회 완료하였습니다.";
//            return new BaseResponse<>(reviews);
//        }catch(BaseException exception){
//            return new BaseResponse<>(exception.getStatus());
//        }
//    }
}
