package com.example.demo.src.order.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.sql.Time;

@Getter
@Setter
@AllArgsConstructor
public class PostOrderRes {
    private Time createdAt;
    private int orderIdx;
    private int totalProductPrice;
    private int deliveryTip;
//    private int coupon;
    private int point;
    private int totalPrice;
    public PostOrderRes() {
    }
}
