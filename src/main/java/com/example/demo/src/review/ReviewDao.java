package com.example.demo.src.review;

import com.example.demo.src.review.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ReviewDao {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }



    public PostReviewRes writeReview(int productIdx, int userIdx , PostReviewReq postReviewReq){

        String reviewType;
        if(postReviewReq.getReview()==null)  reviewType="NOPHOTO";
        else reviewType="PHOTO";

        System.out.println(reviewType);
        String writeReviewQuery="insert into ProductReview(userIdx,productIdx,rating,imageUrl,review,reviewType) " +
                "values(?,?,?,?,?,?)";
        Object[] writeReviewParams = new Object[]{userIdx,productIdx,postReviewReq.getRating(), postReviewReq.getImageUrl(),
        postReviewReq.getReview(), reviewType};
        this.jdbcTemplate.update(writeReviewQuery, writeReviewParams);
        System.out.println("1");
        String getReviewIdx="select last_insert_id()";
        int reviewIdx=this.jdbcTemplate.queryForObject(getReviewIdx,Integer.class);
        String getReviewQuery="select nickname,productName,ProductReview.rating,review,imageUrl,DATE_FORMAT(ProductReview.createdAt,'%Y.%m.%d') as createdAt from Product,User,ProductReview where Product.productIdx=ProductReview.productIdx and reviewIdx=? and User.userIdx=?";
        Object[] getReviewParams=new Object[]{reviewIdx, userIdx};
        System.out.println("2");
        return this.jdbcTemplate.queryForObject(getReviewQuery,
                (rs,rowNum)-> new PostReviewRes(
                        rs.getString("nickname"),
                        rs.getString("productName"),
                        rs.getDouble("rating"),
                        rs.getString("review"),
                        rs.getString("imageUrl"),
                        rs.getString("createdAt")),getReviewParams
                );
    }

    public void deleteReview(int reviewIdx){

        String deleteReviewQuery="update ProductReview set status=\'DELETED\' where reviewIdx=?";
        int deleteReviewParam=reviewIdx;
        this.jdbcTemplate.update(deleteReviewQuery,deleteReviewParam);
    }




   public List<GetProductReviewRes> getReviews(int productIdx){

        String query="select User.nickname, DATE_FORMAT(ProductReview.createdAt,'%Y.%m.%d') as createdAt, rating,imageUrl,review from ProductReview,User where ProductReview.userIdx= User.userIdx and ProductReview.productIdx=?;";
        int param=productIdx;
//        List<GetProductReviewRes> reviews;
       System.out.println("sdv");
        return this.jdbcTemplate.query(query,
                (rs,rowNum)-> new GetProductReviewRes(
                        rs.getString("nickname"),
                        rs.getString("createdAt"),
                        rs.getDouble("rating"),
                        rs.getString("imageUrl"),
                        rs.getString("review")),param
                );

   }

    public List<GetUserReview> getUserReviews(int userIdx){
        List<GetUserReview> reviews= new ArrayList<GetUserReview>();
        String query="select Review.createdAt, rating,photoUrl,comment from Review,User where Review.userIdx= User.userIdx and User.userIdx=? order by Review.createdAt desc;";
        int param=userIdx;
        System.out.println(1);
        reviews = this.jdbcTemplate.query(query,
                (rs, rowNum) -> new GetUserReview(
                        rs.getTime("createdAt"),
                        rs.getInt("rating"),
                        rs.getString("photoUrl"),
                        rs.getString("comment")), param
        );
        System.out.println(reviews);
        List<String> names=new ArrayList<>();
        String getStoreNameQuery="select Store.storeName from Store,Review,User where Review.storeIdx=Store.storeIdx and Review.userIdx= User.userIdx and User.userIdx="+ userIdx;
        names = this.jdbcTemplate.queryForList(getStoreNameQuery,String.class);
        System.out.println(names);
        for(int i=0; i< reviews.size(); i++){
            reviews.get(i).setMenuName(names.get(i));
        }
        return reviews;
    }



    public List<GetProductReviewRes> getReviewsOrderByRating(int storeIdx){

        String query="select User.nickname, DATE_FORMAT(Review.createdAt,'%Y-%m-%d'), rating,photoUrl,comment from Review,User where Review.userIdx= User.userIdx and Review.storeIdx=? order by Review.rating desc;";
        int param=storeIdx;

        return this.jdbcTemplate.query(query,
                (rs,rowNum)-> new GetProductReviewRes(
                        rs.getString("nickname"),
                        rs.getString("createdAt"),
                        rs.getInt("rating"),
                        rs.getString("photoUrl"),
                        rs.getString("comment")),param
        );


    }

}
