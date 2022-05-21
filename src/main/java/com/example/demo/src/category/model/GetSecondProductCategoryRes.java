package com.example.demo.src.category.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.sql.Time;
import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
public class GetSecondProductCategoryRes {
    private String firstCategoryName;
    private String secondCategoryName;
    private String imageUrl;

}
