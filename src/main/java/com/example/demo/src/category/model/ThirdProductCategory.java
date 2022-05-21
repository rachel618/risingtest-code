package com.example.demo.src.category.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.sql.Time;
import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
public class ThirdProductCategory {
    private int thirdCategoryIdx;
    private int secondCategoryIdx;
    private String categoryName;
    private Timestamp createdAt;
    private Timestamp updatedAt;
}