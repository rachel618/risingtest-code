package com.example.demo.src.product.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class GetTodayProductRes {
    private int productIdx;
    private int brandIdx;
    private String brandName; //
    private String productName;
    private int regularPrice;
    private double rating; //
    private int numOfReviews; //
    private int discountRate; //
    private int freeDelivery;
    private int specialPrice;
    private long discountPeriod;
    private String imageUrl;

    public GetTodayProductRes(int productIdx, int brandIdx, String productName, int regularPrice, int freeDelivery, int specialPrice) {
        this.productIdx=productIdx;
        this.brandIdx = brandIdx;
        this.productName = productName;
        this.regularPrice = regularPrice;
        this.freeDelivery = freeDelivery;
        this.specialPrice = specialPrice;
    }

}
