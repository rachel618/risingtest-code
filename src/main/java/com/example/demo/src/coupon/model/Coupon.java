package com.example.demo.src.coupon.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
public class Coupon {
    private int couponIdx;
    private String couponName;
    private Date expirationDate;
    private int minPrice;
    private String couponType;
    private int discountPrice;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private int status;
}
