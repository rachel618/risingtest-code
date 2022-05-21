package com.example.demo.src.product.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class GetPopularRes {
    private String brandName; //
    private String productName;
    private int regularPrice;
    private double rating;  //
    private int numOfReviews;  //
    private int discountRate;
    private boolean freeDelivery;
    private boolean specialPrice;
    private String imageUrl;
}
