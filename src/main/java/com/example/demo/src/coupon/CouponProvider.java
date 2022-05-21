package com.example.demo.src.coupon;

import com.example.demo.config.BaseException;
import com.example.demo.src.coupon.model.GetCouponRes;
import com.example.demo.src.coupon.model.GetCouponRes;
import com.example.demo.utils.JwtService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.demo.config.BaseResponseStatus.DATABASE_ERROR;

@Service
public class CouponProvider {
    private final CouponDao couponDao;
    private final JwtService jwtService;

    final Logger logger = LoggerFactory.getLogger(this.getClass());

    public CouponProvider(CouponDao couponDao, JwtService jwtService) {
        this.couponDao = couponDao;
        this.jwtService = jwtService;
    }
    
    public List<GetCouponRes> getMyCoupons(int userIdx) throws BaseException {
        try{
            List<GetCouponRes> getCouponRes = couponDao.getMyCoupons(userIdx);
            return getCouponRes;
        }
        catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public List<GetCouponRes> getUsableCoupons() throws BaseException {
        try{
            List<GetCouponRes> getCouponRes = couponDao.getUsableCoupons();
            return getCouponRes;
        }
        catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }
}
