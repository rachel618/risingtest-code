package com.example.demo.src.user.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class GetMyShoppingRes {
    private int numOfCoupons;
    private int point;
    private String userRating;
    private int WAITING_FOR_DEPOSIT;
    private int PAYMENT_DONE;
    private int READY_FOR_DELIVERY;
    private int IN_DELIVERY;
    private int DELIVERY_DONE;
    private int WRITE_REVIEW;
    private int numOfReviews;
}
