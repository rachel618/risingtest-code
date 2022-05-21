package com.example.demo.src.coupon;

import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponse;
import com.example.demo.config.BaseResponseStatus;
import com.example.demo.src.coupon.model.GetCouponRes;
import com.example.demo.src.coupon.model.PostCouponReq;
import com.example.demo.utils.JwtService;
import io.jsonwebtoken.Jwts;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.demo.config.BaseResponseStatus.*;


@RestController
@RequestMapping("/coupons")
public class CouponController {
    final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final CouponProvider couponProvider;
    private final CouponService couponService;
    private final JwtService jwtService;
    @Autowired
    public CouponController(CouponProvider couponProvider, CouponService couponService,JwtService jwtService) {
        this.couponProvider = couponProvider;
        this.couponService=couponService;
        this.jwtService = jwtService;
    }
    @ResponseBody
    @GetMapping("")
    BaseResponse<List<GetCouponRes>> getCoupons(@RequestParam(required = true) int userIdx,@RequestParam(required = true) Boolean received){

        try{
            int userIdxByJwt = jwtService.getUserIdx();
            //userIdx와 접근한 유저가 같은지 확인
            if(userIdx != userIdxByJwt){
                return new BaseResponse<>(INVALID_USER_JWT);
            }
            List<GetCouponRes> getCouponsRes;
            if(received == true){
                getCouponsRes=couponProvider.getMyCoupons(userIdx);
                return new BaseResponse<>(SUCCESS_TO_GET_MY_COUPON, getCouponsRes);
            }
            else
                getCouponsRes=couponProvider.getUsableCoupons();
            return new BaseResponse<>(SUCCESS_TO_GET_COUPONS, getCouponsRes);
        }catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    @ResponseBody
    @PostMapping("/{userIdx}")
    public BaseResponse<String> receiveCoupons(@PathVariable("userIdx")int userIdx,@RequestBody PostCouponReq postCouponReq) {
        try {
            int userIdxByJwt = jwtService.getUserIdx();
            //userIdx와 접근한 유저가 같은지 확인
            if(userIdx != userIdxByJwt){
                return new BaseResponse<>(INVALID_USER_JWT);
            }
            couponService.receiveCoupons(userIdx, postCouponReq);
            return new BaseResponse<>(SUCCESS_TO_RECEIVE_COUPONS);
        } catch (BaseException exception) {
            return new BaseResponse<>((exception.getStatus()));
        }
    }
}
