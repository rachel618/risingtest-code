package com.example.demo.src.product.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import sun.util.resources.cldr.ext.TimeZoneNames_en_SS;

import java.sql.Date;
import java.sql.Time;

@Getter
@Setter
@AllArgsConstructor
public class Product {
    private int productIdx;
    private String productName;
    private int firstCategoryIdx;
    private int secondCategoryIdx;
    private int thirdCategoryIdx;
    private int brandIdx;
    private int fixedPrice;
    private int regularPrice;
    private int freeDelivery;
    private int specialPrice;
    private Time createdAt;
    private Time updatedAt;
    private Date expirationDate;
}
