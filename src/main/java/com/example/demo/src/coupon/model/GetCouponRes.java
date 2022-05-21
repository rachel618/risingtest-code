package com.example.demo.src.coupon.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Date;
@Setter
@Getter
@AllArgsConstructor
public class GetCouponRes {
    private String couponName;
    private Date expirationDate;
    private int minPrice;
    private String couponType;
    private int discountPrice;
}
