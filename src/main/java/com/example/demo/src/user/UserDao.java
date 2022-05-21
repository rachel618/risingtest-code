package com.example.demo.src.user;


import com.example.demo.src.user.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class UserDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public List<GetUserRes> getUsers(){
        String getUsersQuery = "select * from User";
        return this.jdbcTemplate.query(getUsersQuery,
                (rs,rowNum) -> new GetUserRes(
                        rs.getInt("userIdx"),
                        rs.getString("nickname"),
                        rs.getString("userEmail"),
                        rs.getString("password"),
                        rs.getInt("numOfFollowers"),
                        rs.getInt("numOfFollowers"),
                        rs.getInt("numOfScraps"),
                        rs.getInt("numOfLikes"),
                        rs.getInt("point"),
                        rs.getString("profileImage"))
                );
    }


    public GetUserRes getUser(int userIdx){
        String getUserQuery = "select * from User where userIdx = ?";
        int getUserParams = userIdx;
        return this.jdbcTemplate.queryForObject(getUserQuery,
                (rs, rowNum) -> new GetUserRes(
                        rs.getInt("userIdx"),
                        rs.getString("nickname"),
                        rs.getString("userEmail"),
                        rs.getString("password"),
                        rs.getInt("numOfFollowers"),
                        rs.getInt("numOfFollowers"),
                        rs.getInt("numOfScraps"),
                        rs.getInt("numOfLikes"),
                        rs.getInt("point"),
                        rs.getString("profileImage")),
                getUserParams);
    }
    

    public int createUser(PostUserReq postUserReq){

        String createUserQuery = "insert into User(userEmail, password, nickname, notification) VALUES (?,?,?,?)";
        Object[] createUserParams = new Object[]{postUserReq.getUserEmail(), postUserReq.getPassword(),
                postUserReq.getNickname(), postUserReq.getNotification()};
        this.jdbcTemplate.update(createUserQuery, createUserParams);

        String lastInserIdQuery = "select last_insert_id()";
        return this.jdbcTemplate.queryForObject(lastInserIdQuery,int.class);
    }

    public int checkEmail(String email){
        String checkEmailQuery = "select exists(select userEmail from User where userEmail = ?)";
        String checkEmailParams = email;
        return this.jdbcTemplate.queryForObject(checkEmailQuery,
                int.class,
                checkEmailParams);

    }

    public int modifyUserName(PatchUserReq patchUserReq){
        String modifyUserNameQuery = "update UserInfo set userName = ? where userIdx = ? ";
        Object[] modifyUserNameParams = new Object[]{patchUserReq.getUserName(), patchUserReq.getUserIdx()};

        return this.jdbcTemplate.update(modifyUserNameQuery,modifyUserNameParams);
    }

    public GetUserRes getPwd(PostLoginReq postLoginReq){
        String getPwdQuery = "select * from User where userEmail = ?";
        String getPwdParams = postLoginReq.getEmail();

        return this.jdbcTemplate.queryForObject(getPwdQuery,
                (rs,rowNum)-> new GetUserRes(
                        rs.getInt("userIdx"),
                        rs.getString("nickname"),
                        rs.getString("userEmail"),
                        rs.getString("password"),
                        rs.getInt("numOfFollowers"),
                        rs.getInt("numOfFollowing"),
                        rs.getInt("numOfScraps"),
                        rs.getInt("numOfLikes"),
                        rs.getInt("point"),
                        rs.getString("profileImage")),
                getPwdParams
                );

    }



    public GetUserProfileRes getUserProfile(int userIdx){
        String getProfileQuery = "select nickname,numOfFollowers,numOfFollowing,numOfScraps, numOfLikes,point, profileImage " +
                "from User where userIdx = "+ userIdx;
        int getProfileParams = userIdx;
        GetUserProfileRes getUserProfileRes=this.jdbcTemplate.queryForObject(getProfileQuery,
                (rs,rowNum)-> new GetUserProfileRes(
                        rs.getString("nickname"),
                        rs.getInt("numOfFollowers"),
                        rs.getInt("numOfFollowing"),
                        rs.getInt("numOfScraps"),
                        rs.getInt("numOfLikes"),
                        rs.getInt("point"),
                        rs.getString("profileImage")));

        System.out.println(getUserProfileRes);
        String getNumOfCouponsQuery="select count(Coupon.couponIdx) from Coupon,User,CouponUser " +
                "where User.userIdx=CouponUser.userIdx and CouponUser.couponIdx=Coupon.couponIdx and User.userIdx=\'"+ userIdx + "\'";
        int numOfCoupons= this.jdbcTemplate.queryForObject(getNumOfCouponsQuery,Integer.class);
        getUserProfileRes.setNumOfCoupons(numOfCoupons);

        int ongoingOrders=0;
        getUserProfileRes.setOngoingOrders(ongoingOrders);
        return getUserProfileRes;
    }

    public int modifyUserPassword(PatchUserPwdReq patchUserPwdReq){
        String modifyUserPwdQuery = "update User set password = ? where userIdx = ? ";
        Object[] modifyUserNameParams = new Object[]{patchUserPwdReq.getNewPwd(), patchUserPwdReq.getUserIdx()};

        return this.jdbcTemplate.update(modifyUserPwdQuery,modifyUserNameParams);
    }

    public GetMyShoppingRes getMyShopping(int userIdx){
        String getMyShopping="select count(Coupon.couponIdx) as numOfCoupons,point,userRating,count(if(Order.status='WAITING_FOR_DEPOSIT',true,null)) as WAITING_FOR_DEPOSIT,\n" +
                "       count(if(Order.status='PAYMENT_DONE',true,null)) as PAYMENT_DONE,\n" +
                "       count(if(Order.status='READY_FOR_DELIVERY',true,null)) as READY_FOR_DELIVERY,\n" +
                "       count(if(Order.status='IN_DELIVERY',true,null)) as IN_DELIVERY,\n" +
                "       count(if(Order.status='DELIVERY_DONE',true,null)) as DELIVERY_DONE,\n" +
                "       count(if(Order.status='WRITE_REVIEW',true,null)) as WRITE_REVIEW,\n" +
                "       count(reviewIdx) as numOfReviews\n" +
                "from CouponUser, User,`Order`,Coupon,ProductReview\n" +
                "where Coupon.couponIdx=CouponUser.couponIdx and CouponUser.userIdx=User.userIdx and `Order`.userIdx=User.userIdx and\n" +
                "      Coupon.expirationDate >=current_date and  ProductReview.userIdx = User.userIdx and User.userIdx=?";
        int getMyShoppingParam=userIdx;
        return this.jdbcTemplate.queryForObject(getMyShopping,
                (rs,rowNum)-> new GetMyShoppingRes(
                        rs.getInt("numOfCoupons"),
                        rs.getInt("point"),
                        rs.getString("userRating"),
                        rs.getInt("WAITING_FOR_DEPOSIT"),
                        rs.getInt("PAYMENT_DONE"),
                        rs.getInt("READY_FOR_DELIVERY"),
                        rs.getInt("IN_DELIVERY"),
                        rs.getInt("DELIVERY_DONE"),
                        rs.getInt("WRITE_REVIEW"),
                        rs.getInt("numOfReviews")),getMyShoppingParam
                );
    }
}
