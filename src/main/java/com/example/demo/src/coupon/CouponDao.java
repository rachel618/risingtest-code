package com.example.demo.src.coupon;

import com.example.demo.src.coupon.model.GetCouponRes;
import com.example.demo.src.coupon.model.PostCouponReq;
import com.example.demo.src.user.model.GetUserProfileRes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class CouponDao {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public List<GetCouponRes> getMyCoupons(int userIdx){
        String getCouponQuery="select couponName,expirationDate,minPrice,couponType, discountPrice from Coupon,User,CouponUser " +
        "where Coupon.status=0 and Coupon.expirationDate>=current_date and"+
                " User.userIdx=CouponUser.userIdx and CouponUser.couponIdx=Coupon.couponIdx and User.userIdx="+ userIdx ;

        return this.jdbcTemplate.query(getCouponQuery,
                (rs,rowNum)-> new GetCouponRes(
                        rs.getString("couponName"),
                        rs.getDate("expirationDate"),
                        rs.getInt("minPrice"),
                        rs.getString("couponType"),
                        rs.getInt("discountPrice")
                ));
    }

    public List<GetCouponRes> getUsableCoupons(){
        String getCouponQuery="select couponName,expirationDate,minPrice,couponType, discountPrice from Coupon " +
                "where Coupon.status=0 and Coupon.expirationDate>=current_date";

        return this.jdbcTemplate.query(getCouponQuery,
                (rs,rowNum)-> new GetCouponRes(
                        rs.getString("couponName"),
                        rs.getDate("expirationDate"),
                        rs.getInt("minPrice"),
                        rs.getString("couponType"),
                        rs.getInt("discountPrice")
                ));
    }
    public void receiveCoupons(int userIdx, PostCouponReq postCouponReq){
        String receiveCouponsQuery = "insert into CouponUser(couponIdx, userIdx) VALUES (?,?)";
        Object[] receiveCouponsParams = new Object[]{postCouponReq.getCouponIdx(), userIdx};
        this.jdbcTemplate.update(receiveCouponsQuery, receiveCouponsParams);
    }
}
