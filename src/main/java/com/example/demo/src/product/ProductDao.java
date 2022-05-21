package com.example.demo.src.product;

import com.example.demo.src.product.model.GetOneProductRes;
import com.example.demo.src.product.model.GetPopularRes;
import com.example.demo.src.product.model.GetProductRes;
import com.example.demo.src.product.model.GetTodayProductRes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Repository
public class ProductDao {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public GetOneProductRes getProduct(int productIdx){
        String getProductQuery="select brandIdx,productName,regularPrice,freeDelivery,specialPrice from Product " +
                "where productIdx=?";
        int param= productIdx;

        GetOneProductRes getProductRes=this.jdbcTemplate.queryForObject(getProductQuery,
                (rs,rowNum)-> new GetOneProductRes(
                        rs.getInt("brandIdx"),
                        rs.getString("productName"),
                        rs.getInt("regularPrice"),
                        rs.getInt("freeDelivery"),
                        rs.getInt("specialPrice")), param);

        System.out.println(getProductRes);
        String getBrandNameQuery="select brandName from Brand where brandIdx="+ getProductRes.getBrandIdx();
        String brandName=this.jdbcTemplate.queryForObject(getBrandNameQuery,String.class);
        getProductRes.setBrandName(brandName);
//        System.out.println(brandName);
        int prodIdx=getProductIdx(getProductRes.getProductName());
        getProductRes.setDiscountRate(getDiscountRate(prodIdx));
        getProductRes.setRating(4.2);
        getProductRes.setNumOfReviews(0);
        getProductRes.setImageUrl(getPhotoUrls(prodIdx));
        System.out.println(getProductRes.getImageUrl());
        return getProductRes;
    }
    public List<GetProductRes> getProducts(){
        String getProductQuery="select productIdx,brandIdx,productName,regularPrice,freeDelivery,specialPrice from Product\n" +
                "where expirationDate not between current_date and date_add(current_date,interval 10 day)\n" +
                "order by rand() limit 3";

        List<GetProductRes> getProductsRes=this.jdbcTemplate.query(getProductQuery,
                (rs,rowNum)-> new GetProductRes(
                        rs.getInt("productIdx"),
                        rs.getInt("brandIdx"),
                        rs.getString("productName"),
                        rs.getInt("regularPrice"),
                        rs.getInt("freeDelivery"),
                        rs.getInt("specialPrice"))
        );

        for(GetProductRes getProductRes : getProductsRes){
            String getBrandNameQuery="select brandName from Brand where brandIdx="+ getProductRes.getBrandIdx();
            String brandName=this.jdbcTemplate.queryForObject(getBrandNameQuery,String.class);
            getProductRes.setBrandName(brandName);
            System.out.println(brandName);
            int prodIdx=getProductRes.getProductIdx();
            getProductRes.setDiscountRate(getDiscountRate(prodIdx));


            getProductRes.setRating(0);
            getProductRes.setNumOfReviews(0);
            getProductRes.setImageUrl(getPhotoUrl(prodIdx));
        }

        System.out.println(getProductsRes);
        return getProductsRes;
    }
    public long discountPeriod(int productIdx){
        String getDateQuery="select expirationDate from Product where productIdx="+ productIdx;
        Date expirationDate=this.jdbcTemplate.queryForObject(getDateQuery, Date.class);

        Calendar getToday = Calendar.getInstance();
        getToday.setTime(new Date()); //금일 날짜
        Calendar cmpDate = Calendar.getInstance();
        cmpDate.setTime(expirationDate); //특정 일자
        long diffSec = (cmpDate.getTimeInMillis() - getToday.getTimeInMillis()) / 1000;
        long diffDays = diffSec / (24*60*60); //일자수 차이
        System.out.println(diffDays);
        return diffDays;
    }
    public int getDiscountRate(int productIdx){
        String query="select fixedPrice from Product where productIdx="+ productIdx;
        int fixedPrice=this.jdbcTemplate.queryForObject(query, Integer.class);

        query="select regularPrice from Product where productIdx="+ productIdx;
        int regularPrice=this.jdbcTemplate.queryForObject(query, Integer.class);

        int discountRate= 100-(100*regularPrice/fixedPrice);
        System.out.println(discountRate);
        return discountRate;
    }
    public int getProductIdx(String productName){
        String query="select productIdx from Product where productName= \'"+ productName+ "\'";
        return this.jdbcTemplate.queryForObject(query, Integer.class);
    }

//    public double getRating(int productIdx){
//        String getRating="se"
//    }
    public List<GetTodayProductRes> getTodayDeal(){
        String getProductQuery="select productIdx,brandIdx,productName,regularPrice,freeDelivery,specialPrice from Product " +
                "where expirationDate between current_date and date_add(current_date,interval 10 day) " +
                "order by rand() limit 4";

        List<GetTodayProductRes> getProductsRes=this.jdbcTemplate.query(getProductQuery,
                (rs,rowNum)-> new GetTodayProductRes(
                        rs.getInt("productIdx"),
                        rs.getInt("brandIdx"),
                        rs.getString("productName"),
                        rs.getInt("regularPrice"),
                        rs.getInt("freeDelivery"),
                        rs.getInt("specialPrice"))
                );

        for(GetTodayProductRes getProductRes : getProductsRes){
            String getBrandNameQuery="select brandName from Brand where brandIdx="+ getProductRes.getBrandIdx();
            String brandName=this.jdbcTemplate.queryForObject(getBrandNameQuery,String.class);
            getProductRes.setBrandName(brandName);
            System.out.println(brandName);
            int productIdx=getProductRes.getProductIdx();
            getProductRes.setDiscountRate(getDiscountRate(productIdx));


            getProductRes.setRating(0);
            getProductRes.setNumOfReviews(0);
            getProductRes.setDiscountPeriod(discountPeriod(productIdx));
            getProductRes.setImageUrl(getPhotoUrl(productIdx));
        }

        System.out.println(getProductsRes);
        return getProductsRes;
    }

    public String getPhotoUrl(int productIdx){
        String query="select imageUrl from ProductImage where productIdx="+ productIdx + " limit 1";
        return this.jdbcTemplate.queryForObject(query,String.class);

    }

    public List<String> getPhotoUrls(int productIdx){
        String query="select imageUrl from ProductImage where productIdx="+ productIdx;
        return this.jdbcTemplate.queryForList(query,String.class);

    }

    public List<GetPopularRes> getPopularProducts(){
        String getPropularProductsquery="select brandName, productName, regularPrice, round(rating,1) as rating,numOfReviews,round(discountRate,0) as discountRate, freeDelivery, specialPrice, imageUrl\n" +
                "from (\n" +
                "                  select brandName,\n" +
                "                         productName,\n" +
                "                         regularPrice,\n" +
                "                         numOfOrders,\n" +
                "                         count(*)                             as numOfReviews,\n" +
                "                         100-100*(regularPrice/Product.fixedPrice) as discountRate,"+
                "                         sum(ProductReview.rating) / count(*) as rating,\n" +
                "                         freeDelivery,\n" +
                "                         specialPrice,\n" +
                "                         imageUrl\n" +
                "                  from Brand,\n" +
                "                       Product,\n" +
                "                       ProductReview\n" +
                "                  where Brand.brandIdx = Product.brandIdx\n" +
                "                    and ProductReview.productIdx = Product.productIdx\n" +
                "                  group by Product.productIdx\n" +
                "              ) A order by numOfOrders desc limit 4";
        return this.jdbcTemplate.query(getPropularProductsquery,
                (rs,rowNum)-> new GetPopularRes(
                        rs.getString("brandName"),
                        rs.getString("productName"),
                        rs.getInt("regularPrice"),
                        rs.getDouble("rating"),
                        rs.getInt("numOfReviews"),
                        rs.getInt("discountRate"),
                        rs.getBoolean("freeDelivery"),
                        rs.getBoolean("specialPrice"),
                        rs.getString("imageUrl")
                        ));
    }
}
