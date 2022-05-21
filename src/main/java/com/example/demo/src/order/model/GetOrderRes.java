package com.example.demo.src.order.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class GetOrderRes {
    private int orderIdx;
    private String storeName;
    private String profileImage;
    private List<String> menus;
    private int totalPrice;
    private String orderDate;

    public GetOrderRes(int orderIdx, int totalPrice, String orderDate) {
        this.orderIdx= orderIdx;
        this.totalPrice = totalPrice;
        this.orderDate = orderDate;
    }
}
