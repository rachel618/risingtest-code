package com.example.demo.src.coupon;

import com.example.demo.config.BaseException;
import com.example.demo.src.coupon.model.PostCouponReq;
import com.example.demo.src.user.UserDao;
import com.example.demo.src.user.UserProvider;
import com.example.demo.src.user.model.PatchUserReq;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import static com.example.demo.config.BaseResponseStatus.*;

@Service
public class CouponService {
    final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final CouponDao couponDao;
    private final CouponDao couponProvider;

    public CouponService(CouponDao couponDao, CouponDao couponProvider) {
        this.couponDao = couponDao;
        this.couponProvider = couponProvider;
    }

    public void receiveCoupons(int userIdx, PostCouponReq postCouponReq) throws BaseException {
        try{
            couponDao.receiveCoupons(userIdx,postCouponReq);
        } catch(Exception exception){
            throw new BaseException(FAILED_TO_GET_COUPON);
        }
    }
}
