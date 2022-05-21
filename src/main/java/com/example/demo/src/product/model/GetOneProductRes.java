package com.example.demo.src.product.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class GetOneProductRes {
    private int brandIdx;
    private String brandName; //
    private String productName;
    private int regularPrice;
    private double rating;  //
    private int numOfReviews;  //
    private int discountRate;  //
    private int freeDelivery;
    private int specialPrice;
    private List<String> imageUrl;

    public GetOneProductRes(int brandIdx, String productName,  int regularPrice, int freeDelivery, int specialPrice) {
        this.brandIdx = brandIdx;
        this.productName = productName;
        this.regularPrice = regularPrice;
        this.freeDelivery = freeDelivery;
        this.specialPrice = specialPrice;
    }
}