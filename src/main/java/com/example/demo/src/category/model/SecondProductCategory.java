package com.example.demo.src.category.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
@Getter
@Setter
@AllArgsConstructor
public class SecondProductCategory {
    private int secondCategoryIdx;
    private int firstCategoryIdx;
    private String categoryName;
    private String imageUrl;
    private Timestamp createdAt;
    private Timestamp updatedAt;
}
